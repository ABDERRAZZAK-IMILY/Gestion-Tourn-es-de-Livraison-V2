package com.logistics.delivery_optimizer.mapper;

import com.logistics.delivery_optimizer.dto.WarehouseRequestDTO;
import com.logistics.delivery_optimizer.dto.WarehouseResponseDTO;
import com.logistics.delivery_optimizer.Model.Warehouse;
import org.springframework.stereotype.Component;


@Component
public class WarehouseMapper {

    public  Warehouse toEntity(WarehouseRequestDTO dto) {

        return Warehouse.builder()
                .name(dto.getName())
                .longitude(dto.getLongitude())
                .latitude(dto.getLatitude())
                .openingTime(dto.getOpeningTime())
                .closingTime(dto.getClosingTime())
                .build();
    }

    public WarehouseResponseDTO toResponseDTO(Warehouse entity) {
    
        return WarehouseResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .openingTime(entity.getOpeningTime())
                .closingTime(entity.getClosingTime())
                .build();
    }
}