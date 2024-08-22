package com.backend.customerDetailMysql.controller;

import com.backend.customerDetailMysql.model.Customer;
import com.backend.customerDetailMysql.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id)
                .map(customer -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Customer>> getAllCustomers(@RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/custom-roles")
    public ResponseEntity<List<Object[]>> getCustomerWithRoleName() {
        List<Object[]> customers = customerService.findCustomerWithRoleName();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
