package com.logistics.delivery_optimizer.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponseDTO {


    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String preferredTimeSlot;
}
