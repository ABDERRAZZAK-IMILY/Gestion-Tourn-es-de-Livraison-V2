package com.logistics.delivery_optimizer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.delivery_optimizer.Model.DeliveryHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {

    Page<DeliveryHistory> findByDeliveryDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);


    @Query("SELECT dh FROM DeliveryHistory dh WHERE dh.customer.id = :customerId")
    Page<DeliveryHistory> findByCustomerId(@Param("customerId") Long customerId, Pageable pageable);
    
    
}
