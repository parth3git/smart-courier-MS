package com.example.SmartCM.Services;

import com.example.SmartCM.DTOS.inTransistDTO;
import com.example.SmartCM.Model.*;
import com.example.SmartCM.Repositary.assignmentRepo;
import com.example.SmartCM.Repositary.locationRepo;
import com.example.SmartCM.Repositary.orderRepo;
import com.example.SmartCM.Repositary.packagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class agentService {

    @Autowired
    orderRepo orderRepo;

    @Autowired
    assignmentRepo assignmentRepo;

    @Autowired
    packagesRepo packageRepo;

    @Autowired
    locationRepo locationRepo;

    public List<DeliveryAssignments> showtask(int agentid) {
        return assignmentRepo.findAssignedTask(agentid);
    }


    public String makeIT(inTransistDTO inTransistDTO) {
        try {
            inTransistDTO.getPackagelist().parallelStream().forEach(pid -> {

                DeliveryAssignments newDA = assignmentRepo.getPkgByPkgId(pid);
                newDA.setDeliveryStatus(deliveryStatus.IN_TRANSIT);

                Packages newpkg = newDA.getPkg();
                newpkg.setDeliveryStatus(deliveryStatus.IN_TRANSIT);
                packageRepo.save(newpkg);

                Locations moveLoc = new Locations();
                moveLoc.setPackages(newpkg);
                moveLoc.setLatitude(20 + Math.random());
                moveLoc.setLongitude(78 + Math.random());
                locationRepo.save(moveLoc);

                assignmentRepo.save(newDA);
            });
        } catch (Exception e) {
            System.out.println("Error while updating delivery status " + e.getMessage());
        }
        return "Packages on transisted";
    }

    @Async
    @Transactional
    @Scheduled(fixedRate = 30000) // every 25 seconds
    public void autoDeliverPackages() {

        List<Packages> inTransitPackages =
                packageRepo.findByDeliveryStatus(deliveryStatus.IN_TRANSIT);

        for (Packages pkg : inTransitPackages) {

            if (pkg.getDeliveryStatus() != deliveryStatus.IN_TRANSIT) continue;

            pkg.setDeliveryStatus(deliveryStatus.DELIVERED);
            packageRepo.save(pkg);

            DeliveryAssignments da =
                    assignmentRepo.findByPkgPackageid(pkg.getPackageid());

            da.setDeliveryStatus(deliveryStatus.DELIVERED);
            da.setDeliveredTime(LocalDateTime.now());
            assignmentRepo.save(da);

            Locations finalLoc = new Locations();
            finalLoc.setPackages(pkg);
            finalLoc.setLatitude(21.1458 + Math.random());
            finalLoc.setLongitude(79.0882 + Math.random());
            locationRepo.save(finalLoc);

            Orders order = pkg.getOrders();

            boolean allDelivered = order.getPackages().stream()
                    .allMatch(p -> p.getDeliveryStatus() == deliveryStatus.DELIVERED);

            if (allDelivered) {
                order.setDeliveryStatus(deliveryStatus.DELIVERED);
                orderRepo.save(order);
            }
        }
    }
}
