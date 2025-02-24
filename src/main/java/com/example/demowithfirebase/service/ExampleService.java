package com.example.demowithfirebase.service;

import com.example.demowithfirebase.exception.NotAllowedException;
import com.example.demowithfirebase.model.CustomerExample;
import com.example.demowithfirebase.repository.CustomerExampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {
    private final CustomerExampleRepository customerExampleRepository;

    public String getList(String email) {
        //Hämtar användare, om användare inte finns kasta ett fel
        CustomerExample exampleCustomer = customerExampleRepository.getCustomerExampleByEmail(email)
                //Kasta eget exception med 403 som sätts i GlobalExceptionHandler
                .orElseThrow(() -> new NotAllowedException("Not a allowed user. Contact your administrator"));

        if (exampleCustomer.getAdmin()) {
            //returnera admin stuff
            return "admin";
        }

        return "user";
    }
}
