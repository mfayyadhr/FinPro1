package com.example.demo.model;
import java.security.Timestamp;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;

public class Akun {
    @NotBlank
    private UUID akunId;
    @NotBlank
    private String NIK;
    @NotBlank
    private String username;
    @NotBlank
    private String status;
    @NotBlank
    private Timestamp passwordUpdatedAt;
    @NotBlank
    private Timestamp createdAt;
    @NotBlank
    private Timestamp updateAt;
    @NotBlank 
    private String passwordHash;
}
