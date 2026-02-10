package com.example.SmartCM.Repositary;

import com.example.SmartCM.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface orderRepo extends JpaRepository<Orders, Integer> {

    @Query(value = "select * from orders where delivery_status = 'PLACED'", nativeQuery = true)
    List<Orders> getByPendingOrder();
}
