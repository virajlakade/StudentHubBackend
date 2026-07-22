package com.viraj.projectbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjectbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectbackendApplication.class, args);
    }

}
