package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.dto.TourResponseDto;
import com.logistics.delivery_optimizer.Model.Tour;
import com.logistics.delivery_optimizer.Model.Warehouse;
import com.logistics.delivery_optimizer.Model.Delivery;
import java.util.List;

public interface TourService {
    
    TourResponseDto createOptimizedTour(Long vehicleId, Long warehouseId, List<Long> deliveryIds);

    double getTotalDistance(Tour tour);
    double calculateTourDistance(Warehouse warehouse, List<Delivery> orderedDeliveries);

    TourResponseDto completeTour(Long tourId);
}