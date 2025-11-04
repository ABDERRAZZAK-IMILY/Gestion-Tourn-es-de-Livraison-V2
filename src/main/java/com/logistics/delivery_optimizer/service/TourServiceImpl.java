package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Tour;
import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.Model.Warehouse;
import com.logistics.delivery_optimizer.mapper.TourMapper;
import com.logistics.delivery_optimizer.repository.DeliveryRepository;
import com.logistics.delivery_optimizer.repository.TourRepository;
import com.logistics.delivery_optimizer.repository.VehicleRepository;
import com.logistics.delivery_optimizer.repository.WarehouseRepository;
import com.logistics.delivery_optimizer.service.optimizer.TourOptimizer;
import com.logistics.delivery_optimizer.util.DistanceCalculator;
import com.logistics.delivery_optimizer.dto.TourResponseDto;
import com.logistics.delivery_optimizer.mapper.TourMapper;

import java.time.LocalDate;
import java.util.List;


public class TourServiceImpl implements TourService {

    private TourOptimizer tourOptimizer;
    
    private TourRepository tourRepository;
    private DeliveryRepository deliveryRepository;
    private VehicleRepository vehicleRepository;
    private WarehouseRepository warehouseRepository;

    private TourMapper tourMapper;


    @Override
    public TourResponseDto createOptimizedTour(Long vehicleId, Long warehouseId, List<Long> deliveryIds) {
        
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        
        List<Delivery> deliveriesToOptimize = deliveryRepository.findAllById(deliveryIds);

        List<Delivery> optimizedList = tourOptimizer.calculateOptimalTour(warehouse, deliveriesToOptimize, vehicle);

        Tour newTour = new Tour();
        newTour.setTourDate(LocalDate.now());
        newTour.setVehicle(vehicle);
        newTour.setWarehouse(warehouse);
        newTour.setDeliveries(optimizedList);

        for (Delivery d : optimizedList) {
            d.setTour(newTour);
            d.setStatus(com.logistics.delivery_optimizer.Model.Enums.DeliveryStatus.IN_TRANSIT);
        }

        Tour savedTour = tourRepository.save(newTour);

        double distance = calculateTourDistance(savedTour.getWarehouse(), savedTour.getDeliveries());
        
        TourResponseDto responseDto = tourMapper.toDto(savedTour);
        
        responseDto.setTotalDistanceKm(distance);

        return responseDto;
    }

    @Override
    public double getTotalDistance(Tour tour) {
        if (tour == null || tour.getDeliveries() == null || tour.getWarehouse() == null) {
            return 0.0;
        }
        return calculateTourDistance(tour.getWarehouse(), tour.getDeliveries());
    }

    @Override
    public double calculateTourDistance(Warehouse startPoint, List<Delivery> orderedDeliveries) {
        
        if (orderedDeliveries == null || orderedDeliveries.isEmpty()) {
            return 0.0;
        }

        double totalDistance = 0.0;
        
        Delivery firstDelivery = orderedDeliveries.get(0);
        totalDistance += DistanceCalculator.distanceBetween(startPoint, firstDelivery);

        for (int i = 0; i < orderedDeliveries.size() - 1; i++) {
            Delivery current = orderedDeliveries.get(i);
            Delivery next = orderedDeliveries.get(i + 1);
            totalDistance += DistanceCalculator.distanceBetween(current, next);
        }

        Delivery lastDelivery = orderedDeliveries.get(orderedDeliveries.size() - 1);
        totalDistance += DistanceCalculator.distanceBetween(startPoint, lastDelivery);

        return totalDistance;
    }



    public void setTourOptimizer(TourOptimizer tourOptimizer) {
        this.tourOptimizer = tourOptimizer;
    }

    public void setTourRepository(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public void setDeliveryRepository(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public void setTourMapper(TourMapper tourMapper) {
        this.tourMapper = tourMapper;
    }
}