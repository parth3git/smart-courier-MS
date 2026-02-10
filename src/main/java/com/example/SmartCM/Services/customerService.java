package com.example.SmartCM.Services;

import com.example.SmartCM.DTOS.PackageStatusDTO;
import com.example.SmartCM.Model.Locations;
import com.example.SmartCM.Model.Orders;
import com.example.SmartCM.Model.Packages;
import com.example.SmartCM.Model.userType;
import com.example.SmartCM.Repositary.adminRepo;
import com.example.SmartCM.Repositary.locationRepo;
import com.example.SmartCM.Repositary.orderRepo;
import com.example.SmartCM.Repositary.packagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class customerService {
    @Autowired
    orderRepo orderRepo;

    @Autowired
    adminRepo adminRepo;

    @Autowired
    locationRepo locationRepo;

    @Autowired
    packagesRepo packagesRepo;

    public String neworder(Orders orders) {
        String orderMakerRole = adminRepo.getRoleById(orders.getCustomerid());

        if (orderMakerRole == null) {
            return "Invalid customer id";
        }

        if (!orderMakerRole.equalsIgnoreCase(userType.CUSTOMER.toString())) {
            return "You are not authorized to place order";
        }

        if (orders.getPackages() == null || orders.getPackages().isEmpty()) {
            return "Order must contain at least one package";
        }

        for (Packages pkg : orders.getPackages()) {
            pkg.setOrders(orders);
        }

        Orders savedOrder = orderRepo.save(orders);

        for (Packages pkg : savedOrder.getPackages()) {

            Locations loc = new Locations();
            loc.setPackages(pkg);
            loc.setLatitude(20.5937);
            loc.setLongitude(78.9629);

            locationRepo.save(loc);
        }

        orderRepo.save(orders);
        return "Order Placed";

    }

    public PackageStatusDTO getpackageinfo(int packageid) {

        Packages packages = packagesRepo.findById(packageid).
                orElseThrow(() -> new RuntimeException("Package Not found"));

        Optional<Locations> locationOpt =
                locationRepo.getLocationByPackageId(packageid);

        PackageStatusDTO packageStatusDTO = new PackageStatusDTO();
        packageStatusDTO.setPackageid(packageid);
        packageStatusDTO.setStatus(packages.getDeliveryStatus());

        if (locationOpt.isPresent()) {
            packageStatusDTO.setLatitude(locationOpt.get().getLatitude());
            packageStatusDTO.setLongitude(locationOpt.get().getLongitude());
        } else {
            packageStatusDTO.setLatitude(0.0);
            packageStatusDTO.setLongitude(0.0);
        }

        return packageStatusDTO;
    }
}
