package com.logistics.delivery_optimizer.dto;

import lombok.Builder;
import lombok.Data;

import com.logistics.delivery_optimizer.Model.Enums.DeliveryStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequestDTO {

    private double longitude;
    private double latitude;
    private double weightKg;
    private double volumeM3;
    private String preferredTimeWindow;

    private Long customerId;
}
