package com.logistics.delivery_optimizer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logistics.delivery_optimizer.dto.DeliveryRequestDTO;
import com.logistics.delivery_optimizer.mapper.DeliveryMapper;
import com.logistics.delivery_optimizer.repository.DeliveryRepository;
import com.logistics.delivery_optimizer.dto.DeliveryResponseDTO;
import com.logistics.delivery_optimizer.Model.Delivery;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class deliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    @Mock
    private DeliveryMapper deliveryMapper;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterVehicle() {
        DeliveryRequestDTO request = new DeliveryRequestDTO();
        Delivery vehicle = new Delivery();
        Delivery savedVehicle = new Delivery();
        DeliveryResponseDTO response = new DeliveryResponseDTO();

        when(deliveryMapper.toEntity(request)).thenReturn(vehicle);
        when(deliveryRepository.save(vehicle)).thenReturn(savedVehicle);
        when(deliveryMapper.toResponseDTO(savedVehicle)).thenReturn(response);

        DeliveryResponseDTO result = deliveryService.createDelivery(request);

        assertNotNull(result);
        assertEquals(response, result);

        verify(deliveryMapper).toEntity(request);
        verify(deliveryRepository).save(vehicle);
        verify(deliveryMapper).toResponseDTO(savedVehicle);
    }
}
