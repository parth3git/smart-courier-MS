package com.example.SmartCM.Repositary;

import com.example.SmartCM.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface managerRepo extends JpaRepository<Users, Integer> {

}
