package com.logistics.delivery_optimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Enums.DeliveryStatus;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    Page<Delivery> findByStatus(DeliveryStatus status, Pageable pageable);
    List<Delivery> findByTourId(Long tourId);

    @Query(value = "SELECT * From Delivery Where Status = :status" , nativeQuery = true)
    List<Delivery> findbyStatus(@Param("status") DeliveryStatus status);
    
}
