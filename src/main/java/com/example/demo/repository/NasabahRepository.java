package com.example.demo.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Nasabah;

@Repository("repo")
public interface NasabahRepository extends JpaRepository<Nasabah, UUID> {
    Nasabah findByCompanyIdAndEmployeeId(UUID companyId, UUID employeeId);
    
    // Ubah return type menjadi Optional<UUID>
    default Optional<UUID> convertToUUID(String uuidString) {
        try {
            return Optional.of(UUID.fromString(uuidString));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
