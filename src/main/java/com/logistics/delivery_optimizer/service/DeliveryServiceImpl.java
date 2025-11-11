package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Customer;
import com.logistics.delivery_optimizer.Model.Delivery;
import com.logistics.delivery_optimizer.Model.Enums.DeliveryStatus;
import com.logistics.delivery_optimizer.dto.CustomerResponseDTO;
import com.logistics.delivery_optimizer.dto.DeliveryRequestDTO;
import com.logistics.delivery_optimizer.dto.DeliveryResponseDTO;
import com.logistics.delivery_optimizer.mapper.DeliveryMapper;
import com.logistics.delivery_optimizer.repository.CustomerRepository;
import com.logistics.delivery_optimizer.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final CustomerRepository customerRepository;


    @Autowired
    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper , CustomerRepository customerRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO requestDTO) {

        Customer customer = customerRepository.findById(requestDTO.getCustomerId()).orElseThrow(()->new RuntimeException("Customer not found with id: " + requestDTO.getCustomerId()));

        Delivery newDelivery = deliveryMapper.toEntity(requestDTO);

        newDelivery.setCustomer(customer);
        
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



    public double getDeliveryStatus(DeliveryStatus status){

        List<Delivery>  deliveries =  deliveryRepository.findbyStatus(status);

        return deliveries.stream().mapToDouble(Delivery::getWeightKg)
                                                        .sum();
    
    }
}