package com.example.demo.service;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.Nasabah;
import com.example.demo.repository.NasabahInterface;

@Service
public class NasabahService {
    @Autowired
    private NasabahInterface nasabahRepository;

    public String login(UUID companyId, UUID employeeId, String password){
        Nasabah nasabah = nasabahRepository.findByCompanyIdAndEmployeeId(companyId, employeeId);
        if (!nasabah.getPassword().equals(password) || nasabah == null) {
            return "berikan konfigutasi yang benar";
        }
        return "anda berhasil login";
    }
    
}
