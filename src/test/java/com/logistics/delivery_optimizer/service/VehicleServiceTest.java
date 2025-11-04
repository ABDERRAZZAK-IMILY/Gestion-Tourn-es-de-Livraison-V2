package com.logistics.delivery_optimizer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logistics.delivery_optimizer.Model.Vehicle;
import com.logistics.delivery_optimizer.dto.VehicleRequestDTO;
import com.logistics.delivery_optimizer.dto.VehicleResponseDTO;
import com.logistics.delivery_optimizer.mapper.VehicleMapper;
import com.logistics.delivery_optimizer.repository.VehicleRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private VehicleServiceImpl vehicleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterVehicle() {
        VehicleRequestDTO request = new VehicleRequestDTO();
        Vehicle vehicle = new Vehicle();
        Vehicle savedVehicle = new Vehicle();
        VehicleResponseDTO response = new VehicleResponseDTO();

        when(vehicleMapper.toEntity(request)).thenReturn(vehicle);
        when(vehicleRepository.save(vehicle)).thenReturn(savedVehicle);
        when(vehicleMapper.toDto(savedVehicle)).thenReturn(response);

        VehicleResponseDTO result = vehicleService.registerVehicle(request);

        assertNotNull(result);
        assertEquals(response, result);

        verify(vehicleMapper).toEntity(request);
        verify(vehicleRepository).save(vehicle);
        verify(vehicleMapper).toDto(savedVehicle);
    }
}
