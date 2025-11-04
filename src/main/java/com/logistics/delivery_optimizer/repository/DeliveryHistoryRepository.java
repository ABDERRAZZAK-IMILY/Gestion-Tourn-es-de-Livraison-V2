package com.logistics.delivery_optimizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logistics.delivery_optimizer.Model.DeliveryHistory;

public interface DeliveryHistoryRepository extends JpaRepository<DeliveryHistory, Long> {

    
    
}
