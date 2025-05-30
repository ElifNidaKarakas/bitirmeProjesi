package com.finansTakipSistemi.bitirmeProjesi.report;


import com.finansTakipSistemi.bitirmeProjesi.model.Transaction;
import com.finansTakipSistemi.bitirmeProjesi.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class BackupService {
    @Autowired
    private TransactionRepository transactionRepository;

    public void backupTransactions(Long userId, String filePath) throws IOException {
        List<Transaction> transactions = transactionRepository.findByUser(
                transactionRepository.findById(userId).orElseThrow().getUser());
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("ID,UserID,Category,Type,Amount,Description,Date\n");
            for (Transaction t : transactions) {
                writer.write(String.format("%d,%d,%s,%s,%.2f,%s,%s\n",
                        t.getId(), t.getUser().getId(), t.getCategory().getName(),
                        t.getType(), t.getAmount(), t.getDescription(), t.getTransactionDate()));
            }
        }
    }
}