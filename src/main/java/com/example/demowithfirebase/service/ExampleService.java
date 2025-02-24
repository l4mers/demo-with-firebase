package com.example.demowithfirebase.service;

import com.example.demowithfirebase.model.CustomerExample;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExampleService {

    private final List<String> list = List.of("company", "company2");

    public String getList() {
        //Om vi har kommit hit har vi en valid användare i securitycontext.
        CustomerExample customerExample = (CustomerExample) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //om vi behöver mera saker än CustomerExample från vår context
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Här kan man tänka sig att vi går igenom listan och returnerar det som har med rätt företag att göra
        for (String name : list) {
            if (customerExample.getCompany().equalsIgnoreCase(name)) {
                return name;
            }
        }
        return "Det finns inget på detta företag";
    }
}
