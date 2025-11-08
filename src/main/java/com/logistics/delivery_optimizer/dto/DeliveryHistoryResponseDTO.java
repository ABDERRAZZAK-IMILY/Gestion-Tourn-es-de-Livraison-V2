package com.logistics.delivery_optimizer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryHistoryResponseDTO {
    private Long id;
    private LocalDate deliveryDate;
    private LocalTime plannedTime;
    private LocalTime actualTime;
    private long delayInMinutes;
    private DayOfWeek dayOfWeek;

    private CustomerResponseDTO customer;
    private TourResponseDto tour;
}