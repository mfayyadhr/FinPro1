package com.example.demo.model;
import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "Nasabah")
@AllArgsConstructor
@Getter
@Setter
public class Nasabah {
    @NotBlank
    @Id
    private String NIK;
    @NotBlank
    private String namaLengkap;
    @NotBlank
    private LocalDate tanggalLahir;
    @NotBlank
    private String tempatLahir;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String nomorHp;
    @NotBlank
    private String namaIbuKandung;
    @NotBlank
    private String alamat;
    @NotBlank
    private boolean isActivated;
}
