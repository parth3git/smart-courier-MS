package com.example.SmartCM.Services;

import com.example.SmartCM.DTOS.updateUserDTO;
import com.example.SmartCM.Model.Users;
import com.example.SmartCM.Model.userType;
import com.example.SmartCM.Repositary.adminRepo;
import com.example.SmartCM.Repositary.deliveryAgentsRepo;
import com.example.SmartCM.Users.DeliveryAgents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class adminService {

    @Autowired
    adminRepo usersRepo;

    @Autowired
    deliveryAgentsRepo deliveryAgentsRepo;

    public String newuser(Users users) {
        try {
            usersRepo.save(users);
            String role = users.getRole().toString();

            if (role.equals(userType.AGENT.toString())) {
                if (!deliveryAgentsRepo.existsById(users.getUid())) {
                    DeliveryAgents deliveryAgents = new DeliveryAgents();
                    deliveryAgents.setAgentId(users.getUid());
                    deliveryAgents.setName(users.getUname());

                    deliveryAgentsRepo.save(deliveryAgents);
                }
            }
        } catch (Exception e) {
            System.out.println("Error while creating user " + e.getMessage());
            return "";
        }
        return "User Created " + users.getUname() + " AS " + users.getRole();
    }

    public String updateUser(updateUserDTO updateUser) {
        try {
            Users newuser = usersRepo.findById(updateUser.getUserid())
                    .orElseThrow(() -> new RuntimeException("User Not Found"));

            String oldrole = newuser.getRole().toString();
            newuser.setRole(updateUser.getRole());
            usersRepo.save(newuser);

            if (!oldrole.equals(userType.ADMIN.toString())) {
                DeliveryAgents deliveryAgents = new DeliveryAgents();
                deliveryAgents.setAgentId(updateUser.getUserid());
                deliveryAgents.setName(newuser.getUname());

                deliveryAgentsRepo.save(deliveryAgents);
            }

        } catch (Exception e) {
            System.out.println("Error While Updating User " + e.getMessage());
        }
        return "User updated";
    }

    public String deleteUser(int id) {
        try {
            Users newuser = usersRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("User Not Found"));

            usersRepo.deleteById(id);

            if (newuser.getRole().toString().equalsIgnoreCase("AGENT")) {
                deliveryAgentsRepo.deleteById(id);
            }

        } catch (Exception e) {
            System.out.println("Error While Deleting User " + e.getMessage());
        }
        return "User Deleted";
    }
}
