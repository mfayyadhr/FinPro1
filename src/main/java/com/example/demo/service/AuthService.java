package com.example.demo.service;
import java.lang.StackWalker.Option;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.demo.model.Nasabah;
import com.example.demo.repository.NasabahRepository;

@Service
public class AuthService {
    
    private final NasabahRepository nasabahRepository;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthService(NasabahRepository nasabahRepository, RedisTemplate<String, String> redisTemplate) {
        this.nasabahRepository = nasabahRepository;
        this.redisTemplate = redisTemplate;
    }

    public String login(String companyIdStr, String employeeIdStr, String password) {
        // Konversi String ke UUID
        Optional<UUID> companyIdOpt = nasabahRepository.convertToUUID(companyIdStr);
        Optional<UUID> employeeIdOpt = nasabahRepository.convertToUUID(employeeIdStr);
        
        // Validasi konversi UUID
        if (companyIdOpt.isEmpty() || employeeIdOpt.isEmpty()) {
            return "Format UUID tidak valid";
        }
        
        // Cari nasabah
        Nasabah nasabah = nasabahRepository.findByCompanyIdAndEmployeeId(
            companyIdOpt.get(), 
            employeeIdOpt.get()
        );
        
        // Validasi nasabah dan password
        if (nasabah == null || !nasabah.getPassword().equals(password)) {
            return "Kombinasi companyId, employeeId, atau password salah";
        }
        
        return "Login berhasil";
    }

    public aktifasi(String employeeId, String atmreceipt, String pinatm){
        Optional<UUID> optionemployeeid = nasabahRepository.convertToUUID(employeeId);

        String storedAtmReceipt = redi
    }
}
