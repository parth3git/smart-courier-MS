package com.example.SmartCM.Controller;

import com.example.SmartCM.DTOS.agentInfoDTO;
import com.example.SmartCM.DTOS.assignPackageDTO;
import com.example.SmartCM.Model.Orders;
import com.example.SmartCM.Services.managerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manager")
public class managerController {

    @Autowired
    managerService managerService;

    @GetMapping("getPendingOrders")
    public List<Orders> getpending() {
        return managerService.getpending();
    }

    @PostMapping("assignPackages")
    public String assignpackage(@RequestBody assignPackageDTO assignPackageDTO) {

        managerService.assignPackageParallel(
                assignPackageDTO.getAgentid(),
                assignPackageDTO.getPackageid());

        return "Assigned";
    }

    @GetMapping("agentsinfo/{agentid}")
    public agentInfoDTO agentinfo(@PathVariable int agentid) {
        return managerService.getagentinfos(agentid);
    }
}
