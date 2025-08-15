package com.example.demo.service;
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
    public String getPinAtm(String customerId){
        return redisTemplate.opsForValue().get("pinatm:"+customerId);
    }
    public String getAtmReceipt(String customerId){
        return redisTemplate.opsForValue().get("atmreceipt:"+customerId);
    }
}
