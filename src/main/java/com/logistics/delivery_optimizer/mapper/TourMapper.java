package com.logistics.delivery_optimizer.mapper;

import com.logistics.delivery_optimizer.dto.TourResponseDto;
import com.logistics.delivery_optimizer.dto.TourRequestDto;
import com.logistics.delivery_optimizer.Model.Tour;
import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.Model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TourMapper {

    private final VehicleMapper vehicleMapper;
    private final DeliveryMapper deliveryMapper;
    private final WarehouseMapper warehouseMapper;

    @Autowired
    public TourMapper(VehicleMapper vehicleMapper, DeliveryMapper deliveryMapper, WarehouseMapper warehouseMapper) {
        this.vehicleMapper = vehicleMapper;
        this.deliveryMapper = deliveryMapper;
        this.warehouseMapper = warehouseMapper;
    }

    public Tour toEntity(TourRequestDto dto, Warehouse warehouse, Vehicle vehicle) {
        if (dto == null) return null;

        return Tour.builder()
                .TourDate(dto.getTourDate())
                .warehouse(warehouse)
                .vehicle(vehicle)
                .build();
    }

    public TourResponseDto toDto(Tour entity) {
        if (entity == null) return null;
        
        return TourResponseDto.builder()
                .id(entity.getId())
                .tourDate(entity.getTourDate())
                .warehouse(warehouseMapper.toResponseDTO(entity.getWarehouse()))
                .vehicle(vehicleMapper.toDto(entity.getVehicle()))
                .deliveries(entity.getDeliveries().stream()
                        .map(deliveryMapper::toResponseDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}