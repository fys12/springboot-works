package com.example.springbootworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootWorksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWorksApplication.class, args);
    }

}
