package com.finansTakipSistemi.bitirmeProjesi.controller;


import com.finansTakipSistemi.bitirmeProjesi.model.Transaction;
import com.finansTakipSistemi.bitirmeProjesi.service.ReportService;
import com.finansTakipSistemi.bitirmeProjesi.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    private ReportService reportService;

    @PostMapping("/add")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(transactionService.addTransaction(transaction));
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<Transaction>> getMonthlyTransactions(
            @RequestParam Long userId,
            @RequestParam int month) {
        return ResponseEntity.ok(transactionService.getMonthlyTransactions(userId, month));
    }

    // YILLIK RAPOR: Belirli bir kullanıcı ve yıl için finansal rapor
    @GetMapping("/yearly")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getYearlyReport(
            @RequestParam Long userId,
            @RequestParam int year) {
        return ResponseEntity.ok(reportService.generateYearlyReport(userId, year));
    }

}