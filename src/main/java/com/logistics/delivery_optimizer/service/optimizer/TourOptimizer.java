package com.logistics.delivery_optimizer.service.optimizer;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.Model.Warehouse;
import java.util.List;

public interface TourOptimizer {
    List<Delivery> calculateOptimalTour(Warehouse startPoint, List<Delivery> deliveries, Vehicle vehicle);
}
