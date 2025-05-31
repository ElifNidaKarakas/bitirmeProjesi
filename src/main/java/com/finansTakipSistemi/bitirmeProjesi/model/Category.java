package com.finansTakipSistemi.bitirmeProjesi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    // Category ile Transaction arasındaki bire-çok ilişkisini belirtir.
    // mappedBy = "category" -> Transaction sınıfındaki 'category' alanı ilişkiyi yönetir.
    // cascade = CascadeType.ALL -> Category silinirse ilişkili tüm Transactions da silinir/güncellenir
    private List<Transaction> transactions;

    // Getters ve Setters — sınıfın özel alanlarına erişmek ve onları değiştirmek için kullanılır.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Transaction> getTransactions() { return transactions; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
}
