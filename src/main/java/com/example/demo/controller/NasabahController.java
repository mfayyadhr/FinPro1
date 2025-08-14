package com.example.demo.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.NasabahService;

@RestController
@RequestMapping("/")
public class NasabahController {
    @Autowired
    NasabahService nasabahService;

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody Map<String, String> body){
        UUID company
    }
}
