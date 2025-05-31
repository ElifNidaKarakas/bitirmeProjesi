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

    // Belirtilen kullanıcı ve ay için aylık rapor oluşturur elirli kullanıcı ve ay için gelir, gider, tasarruf ve en çok harcama yapılan kategoriyi hesaplar.
    public Map<String, Object> generateMonthlyReport(Long userId, int month) {
        // Ayın ilk günü (örneğin 2025-05-01)
        LocalDate start = LocalDate.of(2025, month, 1);
        // Ayın son günü (örneğin 2025-05-31)
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        // Kullanıcıya ait, belirtilen tarih aralığındaki işlemleri alır
        List<Transaction> transactions = transactionRepository.findByUserAndTransactionDateBetween(
                transactionRepository.findById(userId).orElseThrow().getUser(), start, end);

        // Gelirleri toplar
        double totalIncome = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();

        // Giderleri toplar
        double totalExpense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();

        // En çok harcama yapılan kategoriyi bulur
        String topCategory = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        t -> t.getCategory().getName(),               // Kategori ismine göre gruplar
                        Collectors.summingDouble(Transaction::getAmount))) // Her grubun toplam harcaması
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())               // En yüksek toplamı bulur
                .map(Map.Entry::getKey)
                .orElse("None");                                 // Harcama yoksa "None" döner

        // Sonuçları Map olarak döner
        return Map.of(
                "totalIncome", totalIncome,
                "totalExpense", totalExpense,
                "savings", totalIncome - totalExpense,  // Gelir - Gider = Tasarruf
                "topCategory", topCategory
        );
    }

    // Yıllık rapor oluşturmak için
    public Map<String, Object> generateYearlyReport(Long userId, int year) {
        return Map.of();
    }
}
