package com.example.SmartCM.Repositary;

import com.example.SmartCM.Model.Locations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface locationRepo extends JpaRepository<Locations, Integer> {

    @Query(value = "select * from locations where packageid=:packageid ORDER BY updated_at DESC" +
            " LIMIT 1", nativeQuery = true)
    Optional<Locations> getLocationByPackageId(int packageid);
}
