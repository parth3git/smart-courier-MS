package com.example.SmartCM.Services;

import com.example.SmartCM.DTOS.agentInfoDTO;
import com.example.SmartCM.Model.DeliveryAssignments;
import com.example.SmartCM.Model.Orders;
import com.example.SmartCM.Model.Packages;
import com.example.SmartCM.Model.deliveryStatus;
import com.example.SmartCM.Repositary.assignmentRepo;
import com.example.SmartCM.Repositary.deliveryAgentsRepo;
import com.example.SmartCM.Repositary.orderRepo;
import com.example.SmartCM.Repositary.packagesRepo;
import com.example.SmartCM.Users.DeliveryAgents;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class managerService {

    @Autowired
    orderRepo orderRepo;

    @Autowired
    deliveryAgentsRepo deliveryAgentsRepo;

    @Autowired
    packagesRepo packagesRepo;

    @Autowired
    assignmentRepo assignmentRepo;

    @Autowired
    private ApplicationContext context;

    private final ExecutorService executor =
            Executors.newFixedThreadPool(5);

    @PreDestroy
    public void shutdown() {
        executor.shutdown();
    }

    public List<Orders> getpending() {
        return orderRepo.getByPendingOrder();
    }

    @Transactional
    public void assignPackageParallel(int agentid, List<Integer> packageids) {

        for (int pid : packageids) {
            executor.submit(() ->
                    context.getBean(managerService.class)
                            .assignSinglePackage(agentid, pid)
            );
        }
    }

    @Transactional
    public void assignSinglePackage(int agentid, int packageId) {
        DeliveryAgents agent = deliveryAgentsRepo.findById(agentid).orElseThrow(
                () -> new RuntimeException("Agent not exists"));

        Packages pkg = packagesRepo.findById(packageId).
                orElseThrow(() -> new RuntimeException("Package Not Found"));

        if (pkg.getDeliveryStatus() != deliveryStatus.PLACED) {
            return;
        }

        DeliveryAssignments assignments = new DeliveryAssignments();
        assignments.setAgent(agent);
        assignments.setPkg(pkg);
        assignments.setDeliveryStatus(deliveryStatus.ASSIGNED);
        assignmentRepo.save(assignments);

        pkg.setDeliveryStatus(deliveryStatus.ASSIGNED);
        packagesRepo.save(pkg);

        Orders orders = pkg.getOrders();
        boolean allAssigned = orders.getPackages().stream().allMatch(
                p -> p.getDeliveryStatus() == deliveryStatus.ASSIGNED);

        if (allAssigned) {
            orders.setDeliveryStatus(deliveryStatus.ASSIGNED);
            orderRepo.save(orders);
        }
    }

    public agentInfoDTO getagentinfos(int agentid) {

        DeliveryAgents agent = deliveryAgentsRepo.findById(agentid)
                .orElseThrow(() -> new RuntimeException("Agent not found"));

        agentInfoDTO agentInfoDTO = new agentInfoDTO();
        agentInfoDTO.setAgentid(agentid);
        agentInfoDTO.setAssignedpackage(deliveryAgentsRepo.getAssigned(agentid));
        agentInfoDTO.setIntransistpackage(deliveryAgentsRepo.getIntransist(agentid));
        agentInfoDTO.setDeliveredpackage(deliveryAgentsRepo.getDelivered(agentid));

        return agentInfoDTO;
    }
}
