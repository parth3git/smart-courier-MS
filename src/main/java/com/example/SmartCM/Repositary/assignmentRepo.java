package com.example.SmartCM.Repositary;

import com.example.SmartCM.Model.DeliveryAssignments;
import com.example.SmartCM.Model.deliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface assignmentRepo extends JpaRepository<DeliveryAssignments, Integer> {

    @Query(value = "select * from delivery_assignments where agentid=:agentid AND delivery_status='ASSIGNED'", nativeQuery = true)
    List<DeliveryAssignments> findAssignedTask(int agentid);

    @Query(value = "select * from delivery_assignments where packageid=:pid", nativeQuery = true)
    DeliveryAssignments getPkgByPkgId(int pid);

    @Query(value = "select * from delivery_assignments where packageid=:packageid", nativeQuery = true)
    DeliveryAssignments findByPkgPackageid(int packageid);
}
