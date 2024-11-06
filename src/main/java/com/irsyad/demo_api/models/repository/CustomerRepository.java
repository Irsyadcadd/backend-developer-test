package com.irsyad.demo_api.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irsyad.demo_api.models.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
