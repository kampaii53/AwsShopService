package ru.kampaii.shop.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.kampaii.shop")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
