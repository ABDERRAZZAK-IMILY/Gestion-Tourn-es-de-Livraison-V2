package com.logistics.delivery_optimizer.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.logistics.delivery_optimizer.dto.DeliveryRequestDTO;
import com.logistics.delivery_optimizer.dto.DeliveryResponseDTO;
import com.logistics.delivery_optimizer.service.DeliveryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {
    private DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService){
        this.deliveryService = deliveryService;
    }

    @PostMapping
    public DeliveryResponseDTO registerDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO){
        return deliveryService.createDelivery(deliveryRequestDTO);

    }

    @GetMapping("/{deliveryId}")
    public DeliveryResponseDTO getDelivery(@PathVariable Long deliveryId){
        return deliveryService.getDeliveryById(deliveryId);
    }

    @GetMapping
    public List<DeliveryResponseDTO> getAllDeliveries(){
        return deliveryService.getAllDeliveries();
    }

    @DeleteMapping("/{deliveryId}")
    public void deleteDelivery(@PathVariable Long deliveryId){
        deliveryService.deleteDelivery(deliveryId);
    }


}
