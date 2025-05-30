package com.finansTakipSistemi.bitirmeProjesi.service;


import com.finansTakipSistemi.bitirmeProjesi.exception.InvalidTransactionException;
import com.finansTakipSistemi.bitirmeProjesi.model.Transaction;
import com.finansTakipSistemi.bitirmeProjesi.model.User;
import com.finansTakipSistemi.bitirmeProjesi.repository.TransactionRepository;
import com.finansTakipSistemi.bitirmeProjesi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    public Transaction addTransaction(Transaction transaction) {
        if (transaction.getAmount() <= 0) {
            throw new InvalidTransactionException("Amount must be positive.");
        }
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getMonthlyTransactions(Long userId, int month) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate start = LocalDate.of(2025, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        return transactionRepository.findByUserAndTransactionDateBetween(user, start, end);
    }
}