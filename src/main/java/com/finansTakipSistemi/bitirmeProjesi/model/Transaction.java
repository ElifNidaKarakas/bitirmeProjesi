package com.finansTakipSistemi.bitirmeProjesi.model;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Transaction tablosunun birincil anahtarı, otomatik artan değer
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    // Her işlem bir kullanıcıya aittir (çoktan-bire ilişki)
    // Veritabanında "user_id" sütunu ile ilişkilendirilir, boş olamaz
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    // Her işlem bir kategoriye aittir (çoktan-bire ilişki)
    // Veritabanında "category_id" sütunu ile ilişkilendirilir, boş olamaz
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    // İşlem tipi enum olarak saklanır (ör: INCOME veya EXPENSE)
    // Enum değerleri veritabanında string olarak tutulur, boş olamaz
    private TransactionType type;

    @Column(nullable = false)
    // İşlem tutarı
    private Double amount;

    // İşlem açıklaması, isteğe bağlı
    private String description;

    @Column(name = "transaction_date", nullable = false)
    // İşlemin yapıldığı tarih, boş olamaz
    private LocalDate transactionDate;

    // Getter ve Setter metodları
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) { this.transactionDate = transactionDate; }
}
