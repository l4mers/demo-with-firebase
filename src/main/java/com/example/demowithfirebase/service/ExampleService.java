package com.example.demowithfirebase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ExampleService {

    public List<String> getDevicesByTitle(String company) {
        //Här ska den befintliga logiken vara med för att hämta resurser för "company"
        //den logiken bör redan finnas
        return List.of("example of list", "example of list");
    }
}
