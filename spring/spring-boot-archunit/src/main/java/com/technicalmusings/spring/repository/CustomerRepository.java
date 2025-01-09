package com.technicalmusings.spring.repository;

import com.technicalmusings.spring.controller.CustomerController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository {

    @Autowired
    private CustomerController customerController;

    public String getData() throws Exception {
        System.out.println("In the get data class...");
        checkSomething();
        return "data";
    }

    private void checkSomething() throws Exception{
        boolean check = true;
        if (!check) {
            throw new Exception();
        }
    }
}
