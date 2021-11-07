package com.example.administration;

import com.example.administration.maintainance.excel.MainainanceExcelLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdministrationApplication implements CommandLineRunner {


    @Autowired
    public MainainanceExcelLoader mainainanceExcelLoader;

    public static void main(String[] args) {
        SpringApplication.run(AdministrationApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainainanceExcelLoader.loadExcel();
    }
}
