package com.example.demowithfirebase.repository;

import com.example.demowithfirebase.model.CustomerExample;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerExampleRepository extends JpaRepository<CustomerExample, Long> {
    //Optional så vi kan hantera när den är null på ett snyggt sett utan if satser
    //Optional är inte ett måste men bra praxis
    Optional<CustomerExample> getCustomerExampleByEmail(String email);
}
