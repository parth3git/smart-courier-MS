package com.example.SmartCM.Controller;

import com.example.SmartCM.DTOS.updateUserDTO;
import com.example.SmartCM.Model.Users;
import com.example.SmartCM.Services.adminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class adminController {

    @Autowired
    adminService userService;

    @PostMapping(value = "/newUser")
    public String newuser(@RequestBody Users users) {
        return userService.newuser(users);
    }

    @PostMapping(value = "/update")
    public String updateUser(@RequestBody updateUserDTO updateUser) {
        return userService.updateUser(updateUser);
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }
}
