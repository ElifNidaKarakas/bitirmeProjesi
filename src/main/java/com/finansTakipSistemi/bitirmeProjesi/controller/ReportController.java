package com.finansTakipSistemi.bitirmeProjesi.controller;

import com.finansTakipSistemi.bitirmeProjesi.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/reports")  // Bu sınıftaki tüm endpoint'ler "/reports" ile başlar
public class ReportController {

    @Autowired  // Spring, ReportService bileşenini otomatik olarak enjekte eder
    private ReportService reportService;

    // Aylık raporu getiren HTTP GET endpoint'i
    @GetMapping("/monthly")
    @ResponseStatus(HttpStatus.OK)  // Yanıt durum kodu: 200 OK
    public ResponseEntity<Map<String, Object>> getMonthlyReport(
            @RequestParam Long userId,  // Kullanıcı ID'si URL parametresi olarak alınır
            @RequestParam int month) {  // Ay bilgisi URL parametresi olarak alınır

        // ReportService kullanılarak aylık rapor oluşturulur ve 200 OK ile geri döndürülür
        return ResponseEntity.ok(reportService.generateMonthlyReport(userId, month));
    }
}
