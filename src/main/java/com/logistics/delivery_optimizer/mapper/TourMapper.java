package com.logistics.delivery_optimizer.mapper;

import com.logistics.delivery_optimizer.dto.TourResponseDto;
import com.logistics.delivery_optimizer.dto.TourRequestDto;
import com.logistics.delivery_optimizer.Model.Tour;
import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.Model.Warehouse;

import java.util.stream.Collectors;

public class TourMapper {

    private VehicleMapper vehicleMapper;
    private DeliveryMapper deliveryMapper;
    private WarehouseMapper warehouseMapper;

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
    
    public void setVehicleMapper(VehicleMapper vehicleMapper) {
        this.vehicleMapper = vehicleMapper;
    }

    public void setDeliveryMapper(DeliveryMapper deliveryMapper) {
        this.deliveryMapper = deliveryMapper;
    }

    public void setWarehouseMapper(WarehouseMapper warehouseMapper) {
        this.warehouseMapper = warehouseMapper;
    }
}