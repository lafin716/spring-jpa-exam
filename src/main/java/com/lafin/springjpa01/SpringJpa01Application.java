package com.lafin.springjpa01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringJpa01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringJpa01Application.class, args);
    }

}
