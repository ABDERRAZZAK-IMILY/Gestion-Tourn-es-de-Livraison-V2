package com.logistics.delivery_optimizer.Controller;


import com.logistics.delivery_optimizer.dto.CustomerRequestDTO;
import com.logistics.delivery_optimizer.dto.CustomerResponseDTO;
import com.logistics.delivery_optimizer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO createdCustomer = customerService.createCustomer(requestDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@PathVariable Long id) {
        CustomerResponseDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }


    @GetMapping
    public ResponseEntity<Page<CustomerResponseDTO>> getAllCustomers(
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<CustomerResponseDTO> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok(customers);
    }


    @GetMapping("/search")
    public ResponseEntity<Page<CustomerResponseDTO>> searchCustomers(
            @RequestParam String name,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<CustomerResponseDTO> customers = customerService.searchCustomersByName(name, pageable);
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequestDTO requestDTO) {
        CustomerResponseDTO updatedCustomer = customerService.updateCustomer(id, requestDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }


}
