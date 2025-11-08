package com.logistics.delivery_optimizer.mapper;

import com.logistics.delivery_optimizer.Model.Customer;
import com.logistics.delivery_optimizer.dto.CustomerRequestDTO;
import com.logistics.delivery_optimizer.dto.CustomerResponseDTO;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequestDTO dto){

        return Customer.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .preferredTimeSlot(dto.getPreferredTimeSlot())
                .build();

    }

    public CustomerResponseDTO toDto(Customer entity){

        return CustomerResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .preferredTimeSlot(entity.getPreferredTimeSlot())
                .build();

    }

}
