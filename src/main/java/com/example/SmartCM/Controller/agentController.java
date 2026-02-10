package com.example.SmartCM.Controller;

import com.example.SmartCM.DTOS.inTransistDTO;
import com.example.SmartCM.Model.DeliveryAssignments;
import com.example.SmartCM.Services.agentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agent")
public class agentController {

    @Autowired
    agentService agentService;

    @GetMapping("showTask/{agentid}")
    public List<DeliveryAssignments> showTask(@PathVariable int agentid) {
        return agentService.showtask(agentid);
    }

    @PostMapping("makeInTransist")
    public String makeInTransist(@RequestBody inTransistDTO inTransistDTO) {

        return agentService.makeIT(inTransistDTO);
    }
}
