package com.stratifyai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.stratifyai") // Scans controller and root
public class StratifyaiApplication {
    public static void main(String[] args) {
        SpringApplication.run(StratifyaiApplication.class, args);
    }
}