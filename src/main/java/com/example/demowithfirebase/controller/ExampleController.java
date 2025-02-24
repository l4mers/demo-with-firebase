package com.example.demowithfirebase.controller;

import com.example.demowithfirebase.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/example")
public class ExampleController {

    private final ExampleService exampleService;

    //Detta kan hanteras lite olika beroende på vilka status koder du vill skciak tillbaka
    //.ok returnerar 200 och vi hanterar all logik i service kalssen.
    @GetMapping("/list")
    public ResponseEntity<String> GetCompanyList() {
        //extraherar email från token
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Kallar på service metod med email som inparameter
        return ResponseEntity.ok(exampleService.getList(email));
    }
}
