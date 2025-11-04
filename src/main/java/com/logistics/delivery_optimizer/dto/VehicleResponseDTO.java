package com.logistics.delivery_optimizer.dto;

import com.logistics.delivery_optimizer.Model.Enums.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO {
    
    private Long id;
    private String licensePlate;
    private VehicleType type;


}
