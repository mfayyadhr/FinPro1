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
    private UUID idNasabah;
    @NotBlank
    private UUID companyId;
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
    @NotBlank
    private String password;
}
