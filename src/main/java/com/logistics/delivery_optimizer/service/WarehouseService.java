package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.dto.WarehouseRequestDTO;
import com.logistics.delivery_optimizer.dto.WarehouseResponseDTO;
import java.util.List;

public interface WarehouseService {

    WarehouseResponseDTO createWarehouse(WarehouseRequestDTO requestDTO);
    WarehouseResponseDTO getWarehouseById(Long id);
    List<WarehouseResponseDTO> getAllWarehouses();
    
}