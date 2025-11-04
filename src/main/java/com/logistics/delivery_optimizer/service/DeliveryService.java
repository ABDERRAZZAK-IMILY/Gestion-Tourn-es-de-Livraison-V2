package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Enums.DeliveryStatus;
import com.logistics.delivery_optimizer.dto.DeliveryRequestDTO;
import com.logistics.delivery_optimizer.dto.DeliveryResponseDTO;
import java.util.List;


public interface DeliveryService {


    DeliveryResponseDTO createDelivery(DeliveryRequestDTO requestDTO);


    DeliveryResponseDTO getDeliveryById(Long id);


    List<DeliveryResponseDTO> getAllDeliveries();


    List<DeliveryResponseDTO> getDeliveriesByStatus(DeliveryStatus status);


    DeliveryResponseDTO updateDeliveryStatus(Long id, DeliveryStatus newStatus);


    void deleteDelivery(Long id);

    double getDeliveryStatus(DeliveryStatus status);
    
}