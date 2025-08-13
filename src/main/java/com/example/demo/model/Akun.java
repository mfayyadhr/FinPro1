package com.example.demo.model;

import jakarta.validation.constraints.NotBlank;

public class Akun {
    @NotBlank
    private String userID;
    @NotBlank
    private String password;
}
