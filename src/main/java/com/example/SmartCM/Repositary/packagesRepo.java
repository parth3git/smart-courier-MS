package com.example.SmartCM.Repositary;

import com.example.SmartCM.Model.Packages;
import com.example.SmartCM.Model.deliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface packagesRepo extends JpaRepository<Packages, Integer> {

    @Query(value = "select * from packages where delivery_status='IN_TRANSIT'", nativeQuery = true)
    List<Packages> findByDeliveryStatus(deliveryStatus deliveryStatus);

}
