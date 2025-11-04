package com.logistics.delivery_optimizer.service.optimizer;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.Model.Warehouse;
import com.logistics.delivery_optimizer.Model.Enums.VehicleType;
import com.logistics.delivery_optimizer.util.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighborOptimizer implements TourOptimizer {
    @Override
    public List<Delivery> calculateOptimalTour(Warehouse startPoint, List<Delivery> deliveries, Vehicle vehicle) {
        List<Delivery> optimizedTour = new ArrayList<>();
        List<Delivery> remainingDeliveries = new ArrayList<>(deliveries);
        VehicleType vehicleLimits = vehicle.getType();

        double currentWeight = 0.0;
        double currentVolume = 0.0;

        double currentLocationLat = startPoint.getLatitude();
        double currentLocationLon = startPoint.getLongitude();

        while (!remainingDeliveries.isEmpty() && optimizedTour.size() < vehicleLimits.getMaxDeliveries()) {
            
            Delivery nearestDelivery = null;
            int nearestDeliveryIndex = -1;
            double minDistance = Double.MAX_VALUE;

            for (int i = 0; i < remainingDeliveries.size(); i++) {
                Delivery currentDelivery = remainingDeliveries.get(i);
                
                double distance = DistanceCalculator.calculateDistance(
                    currentLocationLat, currentLocationLon,
                    currentDelivery.getLatitude(), currentDelivery.getLongitude()
                );

                if (distance < minDistance) {
                    minDistance = distance;
                    nearestDelivery = currentDelivery;
                    nearestDeliveryIndex = i;
                }
            }

            if (nearestDelivery == null) {
                break;
            }

            double newWeight = currentWeight + nearestDelivery.getWeightKg();
            double newVolume = currentVolume + nearestDelivery.getVolumeM3();

            if (newWeight <= vehicleLimits.getMaxWeightKg() && newVolume <= vehicleLimits.getMaxVolumeM3()) {
                optimizedTour.add(nearestDelivery);
                currentWeight = newWeight;
                currentVolume = newVolume;
                currentLocationLat = nearestDelivery.getLatitude();
                currentLocationLon = nearestDelivery.getLongitude();
                remainingDeliveries.remove(nearestDeliveryIndex);
            } else {
                remainingDeliveries.remove(nearestDeliveryIndex);
            }
        }

        return optimizedTour;
    }

}
