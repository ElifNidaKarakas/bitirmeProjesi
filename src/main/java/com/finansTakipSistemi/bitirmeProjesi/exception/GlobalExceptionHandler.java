package com.finansTakipSistemi.bitirmeProjesi.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Bu sınıf, uygulama genelindeki istisnaları merkezi bir şekilde ele alır.
 * @RestControllerAdvice anotasyonu sayesinde tüm controller'larda geçerlidir.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ValidationException hatalarını yakalar (manuel olarak fırlatılan doğrulama hataları için).
     * Örn: throw new ValidationException("Geçersiz veri");
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidationException(ValidationException ex) {
        // Hata mesajı ve 400 Bad Request yanıtı döndürülür
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Bean Validation (javax/jakarta doğrulama anotasyonları) hatalarını yakalar.
     * Örn: @NotNull, @Size(min = 3), vb. anotasyonlar başarısız olduğunda fırlatılır.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Her bir hata için alan adı ve mesajını alıp hata listesine ekler
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        // Hatalar haritası ve 400 Bad Request döndürülür
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
