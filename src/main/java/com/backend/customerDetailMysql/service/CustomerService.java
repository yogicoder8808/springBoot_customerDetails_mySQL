package com.backend.customerDetailMysql.service;

import com.backend.customerDetailMysql.model.Customer;
import com.backend.customerDetailMysql.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setRole(customer.getRole());
            existingCustomer.setFax(customer.getFax());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setLocality(customer.getLocality());
            existingCustomer.setCity(customer.getCity());
            existingCustomer.setNumber(customer.getNumber());
            return customerRepository.save(existingCustomer);
        }).orElse(null);
    }

    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public List<Object[]> findCustomerWithRoleName() {
        return customerRepository.findCustomerWithRoleName();
    }
}
