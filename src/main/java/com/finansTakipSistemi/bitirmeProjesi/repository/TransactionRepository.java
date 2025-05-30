package com.finansTakipSistemi.bitirmeProjesi.repository;

import com.finansTakipSistemi.bitirmeProjesi.model.Transaction;
import com.finansTakipSistemi.bitirmeProjesi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserAndTransactionDateBetween(User user, LocalDate start, LocalDate end);

    List<Transaction> findByUser(User user);
}