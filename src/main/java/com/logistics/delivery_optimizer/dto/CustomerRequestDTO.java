package com.logistics.delivery_optimizer.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerRequestDTO {
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Address cannot be blank")
    private String address;
    @NotNull(message = "Latitude cannot be null")
    @Range(min = -90, max = 90, message = "Latitude must be between -90 and 90")
    private double latitude;
    @NotNull(message = "Longitude cannot be null")
    @Range(min = -180, max = 180, message = "Longitude must be between -180 and 180")
    private double longitude;

    private String preferredTimeSlot;

}
