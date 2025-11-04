package com.logistics.delivery_optimizer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.Model.Enums.VehicleType;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findByLicensePlate(String licensePlate);

    List<Vehicle> findByType(VehicleType Type);
}
