package com.logistics.delivery_optimizer.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDTO {

    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String preferredTimeSlot;

}
