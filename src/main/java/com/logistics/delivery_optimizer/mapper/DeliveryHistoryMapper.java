package com.logistics.delivery_optimizer.mapper;

import com.logistics.delivery_optimizer.Model.DeliveryHistory;
import com.logistics.delivery_optimizer.dto.DeliveryHistoryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryHistoryMapper {

    private final CustomerMapper customerMapper;
    private final TourMapper tourMapper;

    @Autowired
    public DeliveryHistoryMapper(CustomerMapper customerMapper, TourMapper tourMapper) {
        this.customerMapper = customerMapper;
        this.tourMapper = tourMapper;
    }

    public DeliveryHistoryResponseDTO toResponseDTO(DeliveryHistory entity) {
        if (entity == null) return null;

        return DeliveryHistoryResponseDTO.builder()
                .id(entity.getId())
                .deliveryDate(entity.getDeliveryDate())
                .plannedTime(entity.getPlannedTime())
                .actualTime(entity.getActualTime())
                .delayInMinutes(entity.getDelayInMinutes())
                .dayOfWeek(entity.getDayOfWeek())
                .customer(customerMapper.toDto(entity.getCustomer()))
                .tour(tourMapper.toDto(entity.getTour()))
                .build();
    }
}