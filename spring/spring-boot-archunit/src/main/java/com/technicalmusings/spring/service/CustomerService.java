package com.technicalmusings.spring.service;

import com.technicalmusings.spring.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class CustomerService {

    private Logger logger = Logger.getLogger(CustomerService.class.getName());

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
