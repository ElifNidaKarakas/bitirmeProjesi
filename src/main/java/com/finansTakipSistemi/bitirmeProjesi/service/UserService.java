package com.finansTakipSistemi.bitirmeProjesi.service;


import com.finansTakipSistemi.bitirmeProjesi.model.User;
import com.finansTakipSistemi.bitirmeProjesi.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Yeni kullanıcı kaydı oluşturur ve veritabanına kaydeder
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    // Verilen id'ye sahip kullanıcıyı bulur, yoksa hata fırlatır
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
