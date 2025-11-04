package com.logistics.delivery_optimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.delivery_optimizer.Model.Warehouse;
import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    Optional<Warehouse> findByName(String name);
    
}
