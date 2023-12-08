package com.sector.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class Health {

    @GetMapping()
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }
}