package com.example.SmartCM.Controller;

import com.example.SmartCM.DTOS.PackageStatusDTO;
import com.example.SmartCM.Model.Orders;
import com.example.SmartCM.Services.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class customerController {

    @Autowired
    customerService customerService;

    @PostMapping(value = "newOrder")
    public String neworder(@RequestBody Orders orders) {
        return customerService.neworder(orders);
    }

    @GetMapping(value = "getPackageInfo/{packageid}")
    public PackageStatusDTO getpackage(@PathVariable int packageid) {
        return customerService.getpackageinfo(packageid);
    }
}
