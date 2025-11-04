package com.logistics.delivery_optimizer.Controller;

import com.logistics.delivery_optimizer.Model.Tour;
import com.logistics.delivery_optimizer.dto.TourResponseDto;
import com.logistics.delivery_optimizer.service.TourService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/api/v1/tours")
public class TourController {

    private TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    public static class OptimizeRequestDTO {
        public Long vehicleId;
        public Long warehouseId;
        public List<Long> deliveryIds;
    }

    @PostMapping("/optimize")
    public TourResponseDto createOptimizedTour(@RequestBody OptimizeRequestDTO request) {
        
        return tourService.createOptimizedTour(
            request.vehicleId,
            request.warehouseId,
            request.deliveryIds
        );
    }
}