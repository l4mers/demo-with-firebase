package com.example.demowithfirebase.controller;

import com.example.demowithfirebase.model.CustomerExample;
import com.example.demowithfirebase.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/getList/{company}")
    public ResponseEntity<List<String>> getListByTitle(@AuthenticationPrincipal CustomerExample customer){
        return ResponseEntity.ok(exampleService.getDevicesByTitle(customer.getCompany()));
    }
}
