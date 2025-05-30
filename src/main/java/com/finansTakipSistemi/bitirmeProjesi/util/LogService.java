package com.finansTakipSistemi.bitirmeProjesi.util;


import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class LogService {
    public void logAction(String user, String action) throws IOException {
        try (FileWriter writer = new FileWriter ("logs.txt", true)){
            writer.write(String.format("%s - User: %s, Action: %s\n", LocalDateTime.now(), user, action));
        }
    }
}