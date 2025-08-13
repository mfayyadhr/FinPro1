package com.example.demo.model;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "Nasabah")
public class Nasabah {
    @NotBlank
    private String nama;
    @NotBlank
    private LocalDate tanggalLahir;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String handphone;
    @NotBlank
    private String alamat;
}
