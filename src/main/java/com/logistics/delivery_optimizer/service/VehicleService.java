package com.logistics.delivery_optimizer.service;

import java.util.List;

import com.logistics.delivery_optimizer.dto.VehicleRequestDTO;
import com.logistics.delivery_optimizer.dto.VehicleResponseDTO;

public interface VehicleService  {
    public VehicleResponseDTO registerVehicle(VehicleRequestDTO vehicleRequestDTO);

    public VehicleResponseDTO getVehicleInfo(Long vehicleId);

    public List<VehicleResponseDTO> getAllVehicles();

    public void deleteVehicle(Long id);
}
