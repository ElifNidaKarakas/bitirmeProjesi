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
@Entity  // Bu sınıfın bir JPA varlığı olduğunu belirtir, veritabanında tabloya karşılık gelir
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Birincil anahtar ve otomatik artan değer
    private Long id;

    @Column()
    @NotNull(message = "Kullanıcı adı gereklidir")  // Boş olamaz
    @Size(min = 2, max = 25, message = "Kullanıcı adı 2-25 karakter uzunluğunda olmalıdır") // Karakter uzunluğu kontrolü
    private String username;

    @NotNull(message = "Şifre gereklidir")  // Boş olamaz
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")  // Minimum uzunluk kontrolü
    @Column()
    private String password;

    @Column(unique = true)  // Bu sütundaki değerler benzersiz olmalıdır (eşsiz e-posta)
    @NotNull(message = "E-posta gereklidir")  // Boş olamaz
    @Email(message = "Geçerli bir e-posta adresi girin")  // E-posta formatı kontrolü
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    // User ve Transaction arasında bire çok ilişki (bir kullanıcı birçok işlem yapabilir)
    // "user" alanı Transaction sınıfında karşılık gelir
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
