package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.dto.CustomerRequestDTO;
import com.logistics.delivery_optimizer.dto.CustomerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO);

    CustomerResponseDTO getCustomerById(Long id);

    Page<CustomerResponseDTO> getAllCustomers(Pageable pageable);

    Page<CustomerResponseDTO> searchCustomersByName(String name, Pageable pageable);

    CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO);

    void deleteCustomer(Long id);
}
