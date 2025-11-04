package com.logistics.delivery_optimizer.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logistics.delivery_optimizer.dto.VehicleRequestDTO;
import com.logistics.delivery_optimizer.dto.VehicleResponseDTO;
import com.logistics.delivery_optimizer.service.VehicleService;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public VehicleResponseDTO registerVehicle(@RequestBody VehicleRequestDTO vehicleRequestDTO){
    return vehicleService.registerVehicle(vehicleRequestDTO);

    }

    @GetMapping("/{vehicleId}")
    public VehicleResponseDTO getVehicle(@PathVariable Long vehicleId){
        return vehicleService.getVehicleInfo(vehicleId);
    }
    
    @GetMapping
    public List<VehicleResponseDTO> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("/{vehicleId}")
    public void deleteVehicle(@PathVariable Long vehicleId){
        vehicleService.deleteVehicle(vehicleId);
    }
    
    
}
