package com.logistics.delivery_optimizer.service;

import com.logistics.delivery_optimizer.Model.Customer;
import com.logistics.delivery_optimizer.dto.CustomerRequestDTO;
import com.logistics.delivery_optimizer.dto.CustomerResponseDTO;
import com.logistics.delivery_optimizer.mapper.CustomerMapper;
import com.logistics.delivery_optimizer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    final CustomerRepository customerRepository;
    final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerRequestDTO requestDTO){

        Customer customer = customerMapper.toEntity(requestDTO);

        Customer customersave = customerRepository.save(customer);

        return  customerMapper.toDto(customersave);
    }
    @Override
    public CustomerResponseDTO getCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        CustomerResponseDTO response = customerMapper.toDto(customer);
        return response;
    }

    @Override
    public void deleteCustomer(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        customerRepository.delete(customer);
    }

    @Override
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO requestDTO){
        Customer customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        customer.setName(requestDTO.getName());
        customer.setAddress(requestDTO.getAddress());
        Customer updatedCustomer = customerRepository.save(customer);
        CustomerResponseDTO response = customerMapper.toDto(updatedCustomer);
        return response;
    }

    @Override
    public Page<CustomerResponseDTO> getAllCustomers(Pageable pageable){
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerMapper::toDto);
    }

    @Override
    public Page<CustomerResponseDTO> searchCustomersByName(String name, Pageable pageable){
        Page<Customer> customers = customerRepository.findByNameContainingIgnoreCase(name, pageable);
        return customers.map(customerMapper::toDto);
    }



}
