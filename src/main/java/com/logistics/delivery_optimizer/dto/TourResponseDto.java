package com.logistics.delivery_optimizer.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TourResponseDto {
    private Long id;
    private LocalDate tourDate;

    private WarehouseResponseDTO warehouse;
    private VehicleResponseDTO vehicle;
    private List<DeliveryResponseDTO> deliveries;
    private double totalDistanceKm;
}