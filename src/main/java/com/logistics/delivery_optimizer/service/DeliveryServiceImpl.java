package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Enums.DeliveryStatus;
import com.logistics.delivery_optimizer.dto.DeliveryRequestDTO;
import com.logistics.delivery_optimizer.dto.DeliveryResponseDTO;
import com.logistics.delivery_optimizer.mapper.DeliveryMapper;
import com.logistics.delivery_optimizer.repository.DeliveryRepository;

import java.util.List;
import java.util.stream.Collectors;


public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;
    private DeliveryMapper deliveryMapper;


    @Override
    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO requestDTO) {
        Delivery newDelivery = deliveryMapper.toEntity(requestDTO);
        
        Delivery savedDelivery = deliveryRepository.save(newDelivery);
        
        return deliveryMapper.toResponseDTO(savedDelivery);
    }

    @Override
    public DeliveryResponseDTO getDeliveryById(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
        
        return deliveryMapper.toResponseDTO(delivery);
    }

    @Override
    public List<DeliveryResponseDTO> getAllDeliveries() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        
        return deliveries.stream()
                .map(deliveryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<DeliveryResponseDTO> getDeliveriesByStatus(DeliveryStatus status) {
        List<Delivery> deliveries = deliveryRepository.findByStatus(status);
        
        return deliveries.stream()
                .map(deliveryMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DeliveryResponseDTO updateDeliveryStatus(Long id, DeliveryStatus newStatus) {
        
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
        
        delivery.setStatus(newStatus);
        
        Delivery updatedDelivery = deliveryRepository.save(delivery);
        
        return deliveryMapper.toResponseDTO(updatedDelivery);
    }

    @Override
    public void deleteDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found with id: " + id));
                
        deliveryRepository.delete(delivery);
    }


    public void setDeliveryRepository(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void setDeliveryMapper(DeliveryMapper deliveryMapper) {
        this.deliveryMapper = deliveryMapper;
    }

    public double getDeliveryStatus(DeliveryStatus status){

        List<Delivery>  deliveries =  deliveryRepository.findbyStatus(status);

        return deliveries.stream().mapToDouble(Delivery::getWeightKg)
                                                        .sum();
    
    }
}