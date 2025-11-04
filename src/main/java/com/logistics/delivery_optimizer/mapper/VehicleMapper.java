package com.logistics.delivery_optimizer.mapper;

import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.dto.VehicleRequestDTO;
import com.logistics.delivery_optimizer.dto.VehicleResponseDTO;
import org.springframework.stereotype.Component;


@Component
public class VehicleMapper {
    
    public Vehicle toEntity(VehicleRequestDTO dto) {
        return Vehicle.builder()
                .licensePlate(dto.getLicensePlate())
                .type(dto.getType())
                .build();
    }

    public VehicleResponseDTO toDto(Vehicle entity){
        return VehicleResponseDTO.builder()
                .id(entity.getId())
                .licensePlate(entity.getLicensePlate())
                .type(entity.getType())
                .build();
    }


}
