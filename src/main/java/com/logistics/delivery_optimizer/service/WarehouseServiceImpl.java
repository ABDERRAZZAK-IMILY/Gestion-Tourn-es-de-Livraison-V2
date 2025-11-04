package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Warehouse;
import com.logistics.delivery_optimizer.dto.WarehouseRequestDTO;
import com.logistics.delivery_optimizer.dto.WarehouseResponseDTO;
import com.logistics.delivery_optimizer.mapper.WarehouseMapper;
import com.logistics.delivery_optimizer.repository.WarehouseRepository;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseServiceImpl implements WarehouseService {

    private WarehouseRepository warehouseRepository;
    private WarehouseMapper warehouseMapper;

    @Override
    public WarehouseResponseDTO createWarehouse(WarehouseRequestDTO requestDTO) {
        Warehouse warehouse = warehouseMapper.toEntity(requestDTO);
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);
        return warehouseMapper.toResponseDTO(savedWarehouse);
    }

    @Override
    public WarehouseResponseDTO getWarehouseById(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));
        return warehouseMapper.toResponseDTO(warehouse);
    }

    @Override
    public List<WarehouseResponseDTO> getAllWarehouses() {
        return warehouseRepository.findAll().stream()
                .map(warehouseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void setWarehouseRepository(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public void setWarehouseMapper(WarehouseMapper warehouseMapper) {
        this.warehouseMapper = warehouseMapper;
    }
}