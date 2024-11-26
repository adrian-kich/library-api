package com.adriankich.library.domain.context;

import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ApplicationContext {
    private static final ApplicationContext INSTANCE = new ApplicationContext();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    public LocalDate getLocalDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    public String getStringDate(LocalDate date) {
        return FORMATTER.format(date);
    }

    public String formatCpf(String cpf) {
        return cpf.replaceAll("\\.|-", "");
    }
}
