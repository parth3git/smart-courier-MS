package com.example.SmartCM.Repositary;

import com.example.SmartCM.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface adminRepo extends JpaRepository<Users, Integer> {

    @Query(value = "select role from users where uid=?1", nativeQuery = true)
    String getRoleById(int customerid);
}
