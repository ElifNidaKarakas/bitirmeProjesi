package com.finansTakipSistemi.bitirmeProjesi.controller;

import com.finansTakipSistemi.bitirmeProjesi.model.User;
import com.finansTakipSistemi.bitirmeProjesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController  // Bu sınıf bir REST API controller'dır
@RequestMapping("/users")  // Bu sınıftaki tüm endpoint'ler "/users" ile başlar
public class UserController {

    @Autowired  // Spring, UserService sınıfını otomatik olarak enjekte eder
    private UserService userService;

    // KULLANICI KAYIT: Kullanıcıdan alınan verilerle yeni kullanıcı oluşturur
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // KULLANICI ÖZET BİLGİSİ: Belirli bir kullanıcı ID'sine göre kullanıcı bilgisi döner
    @GetMapping("/{id}/summary")
    public ResponseEntity<User> getUserSummary(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }
}
