package com.logistics.delivery_optimizer.Model.Enums;

import lombok.Getter;

@Getter
public enum VehicleType {
    BIKE(50.0, 0.5, 15),
    VAN(1000.0, 8.0, 50),
    TRUCK(5000.0, 40.0, 100); 

    private final double maxWeightKg;
    private final double maxVolumeM3;
    private final int maxDeliveries;

    VehicleType(double maxWeightKg, double maxVolumeM3, int maxDeliveries) {
        this.maxWeightKg = maxWeightKg;
        this.maxVolumeM3 = maxVolumeM3;
        this.maxDeliveries = maxDeliveries;
    }

    
}
