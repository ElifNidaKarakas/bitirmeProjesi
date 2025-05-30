package com.finansTakipSistemi.bitirmeProjesi.controller;

import com.finansTakipSistemi.bitirmeProjesi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/monthly")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, Object>> getMonthlyReport(
            @RequestParam Long userId,
            @RequestParam int month) {
        return ResponseEntity.ok(reportService.generateMonthlyReport(userId, month));
    }
}