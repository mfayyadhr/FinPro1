package com.example.demo.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AuthService;

@RestController
@RequestMapping("/")
public class NasabahController {
    @Autowired
    AuthService nasabahService;

    @PostMapping("/login")
    public ResponseEntity<String>login(@RequestBody Map<String, String> body){
        String companyId = body.get("companyId");
        String employeeId = body.get("employeeId");
        String password = body.get("password");

        return new ResponseEntity<>(nasabahService.login(companyId, employeeId, password),HttpStatus.OK);
    }
}
