package com.example.demowithfirebase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ExampleService {

    public String getList() {
        return "Returnera lsitan baserad på företag och eventuell logik";
    }
}
