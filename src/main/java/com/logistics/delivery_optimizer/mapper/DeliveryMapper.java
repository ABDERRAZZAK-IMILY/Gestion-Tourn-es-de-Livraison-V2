package com.logistics.delivery_optimizer.mapper;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Enums.DeliveryStatus;
import com.logistics.delivery_optimizer.dto.DeliveryRequestDTO;
import com.logistics.delivery_optimizer.dto.DeliveryResponseDTO;

public class DeliveryMapper {


    public Delivery toEntity(DeliveryRequestDTO dto) {
        
        return Delivery.builder()
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .weightKg(dto.getWeightKg())
                .volumeM3(dto.getVolumeM3())
                .preferredTimeWindow(dto.getPreferredTimeWindow())
                
                .status(DeliveryStatus.PENDING)
                
                .build();
    }


    public DeliveryResponseDTO toResponseDTO(Delivery entity) {
        
        return DeliveryResponseDTO.builder()
                .id(entity.getId())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .weightKg(entity.getWeightKg())
                .volumeM3(entity.getVolumeM3())
                .preferredTimeWindow(entity.getPreferredTimeWindow())
                .status(entity.getStatus())
                .build();
    }
}