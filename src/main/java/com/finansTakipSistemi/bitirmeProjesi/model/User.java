package com.finansTakipSistemi.bitirmeProjesi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    @NotNull(message = "Kullanıcı adı gereklidir")
    @Size(min = 3, max = 20, message = "Kullanıcı adı 3-20 karakter olmalıdır")
    private String username;

    @NotNull(message = "Şifre gereklidir")
    @Size(min = 8, message = "Şifre en az 8 karakter olmalıdır")
    @Column()
    private String password;

    @Column(unique = true)
    @NotNull(message = "E-posta gereklidir")
    @Email(message = "Geçerli bir e-posta adresi girin")
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }


}