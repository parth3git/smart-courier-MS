package com.example.SmartCM.Repositary;

import com.example.SmartCM.Users.DeliveryAgents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface deliveryAgentsRepo extends JpaRepository<DeliveryAgents, Integer> {

    @Query(value = "select packageid from delivery_assignments where delivery_status='ASSIGNED' AND agentid=:agentid", nativeQuery = true)
    List<Integer> getAssigned(int agentid);

    @Query(value = "select packageid from delivery_assignments where delivery_status='IN_TRANSIT' AND agentid=:agentid", nativeQuery = true)
    List<Integer> getIntransist(int agentid);

    @Query(value = "select packageid from delivery_assignments where delivery_status='DELIVERED' AND agentid=:agentid", nativeQuery = true)
    List<Integer> getDelivered(int agentid);

}
