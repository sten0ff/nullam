package com.example.nullam;

import com.example.nullam.models.Company;
import com.example.nullam.models.Event;
import com.example.nullam.models.Member;
import com.example.nullam.repository.CompanyRepository;
import com.example.nullam.repository.EventRepository;
import com.example.nullam.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class NullamApplication {

    public static void main(String[] args) {
        SpringApplication.run(NullamApplication.class, args);
    }
}
