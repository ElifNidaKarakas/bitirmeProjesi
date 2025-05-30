package com.finansTakipSistemi.bitirmeProjesi.service;
import com.finansTakipSistemi.bitirmeProjesi.model.Transaction;
import com.finansTakipSistemi.bitirmeProjesi.model.TransactionType;
import com.finansTakipSistemi.bitirmeProjesi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Map<String, Object> generateMonthlyReport(Long userId, int month) {
        LocalDate start = LocalDate.of(2025, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        List<Transaction> transactions = transactionRepository.findByUserAndTransactionDateBetween(
                transactionRepository.findById(userId).orElseThrow().getUser(), start, end);

        double totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
        double totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
        String topCategory = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getName(),
                        Collectors.summingDouble(Transaction::getAmount)))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("None");

        return Map.of(
                "totalIncome", totalIncome,
                "totalExpense", totalExpense,
                "savings", totalIncome - totalExpense,
                "topCategory", topCategory
        );
    }
}