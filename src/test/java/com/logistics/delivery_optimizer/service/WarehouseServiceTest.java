package com.logistics.delivery_optimizer.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logistics.delivery_optimizer.dto.WarehouseRequestDTO;
import com.logistics.delivery_optimizer.dto.WarehouseResponseDTO;
import com.logistics.delivery_optimizer.Model.Warehouse;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.logistics.delivery_optimizer.mapper.WarehouseMapper;
import com.logistics.delivery_optimizer.repository.WarehouseRepository;

class WarehouseServiceTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private WarehouseMapper warehouseMapper;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterWarehouse() {
        WarehouseRequestDTO request = new WarehouseRequestDTO();
        Warehouse warehouse = new Warehouse();
        Warehouse savedWarehouse = new Warehouse();
        WarehouseResponseDTO response = new WarehouseResponseDTO();

        when(warehouseMapper.toEntity(request)).thenReturn(warehouse);
        when(warehouseRepository.save(warehouse)).thenReturn(savedWarehouse);
        when(warehouseMapper.toResponseDTO(savedWarehouse)).thenReturn(response);

        WarehouseResponseDTO result = warehouseService.createWarehouse(request);

        assertNotNull(result);
        assertEquals(response, result);

        verify(warehouseMapper).toEntity(request);
        verify(warehouseRepository).save(warehouse);
        verify(warehouseMapper).toResponseDTO(savedWarehouse);
    }
}
