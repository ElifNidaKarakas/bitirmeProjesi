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

    // Yeni işlem ekler, tutar pozitif değilse hata fırlatır
    public Transaction addTransaction(Transaction transaction) {
        if (transaction.getAmount() <= 0) {
            throw new InvalidTransactionException("Amount must be positive.");
        }
        // İşlem kaydedilir ve döner
        return transactionRepository.save(transaction);
    }

    // Belirtilen kullanıcı ve ay için o aya ait işlemleri getirir
    public List<Transaction> getMonthlyTransactions(Long userId, int month) {
        // Kullanıcı var mı diye kontrol eder, yoksa RuntimeException fırlatır
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Ayın ilk günü (örneğin 2025-05-01)
        LocalDate start = LocalDate.of(2025, month, 1);
        // Ayın son günü (örneğin 2025-05-31)
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Kullanıcı ve tarih aralığına göre işlemleri sorgular
        return transactionRepository.findByUserAndTransactionDateBetween(user, start, end);
    }
}