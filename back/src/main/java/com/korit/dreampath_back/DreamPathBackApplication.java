package com.korit.dreampath_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DreamPathBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamPathBackApplication.class, args);
    }

}
