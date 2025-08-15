package com.example.demo.model;
import java.security.Timestamp;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Akun")
public class Akun {
    @NotBlank
    @Id
    private UUID akunId;
    @NotBlank
    @Column(unique = true)
    private String NIK;
    @NotBlank
    @Column(unique = true)
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
