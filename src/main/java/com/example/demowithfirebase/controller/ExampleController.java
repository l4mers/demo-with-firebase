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


@RestController
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleController {

    private final ExampleService exampleService;

    @GetMapping("/list")
    public ResponseEntity<String> GetCompanyList() {
        return ResponseEntity.ok(exampleService.getList());
    }
}
