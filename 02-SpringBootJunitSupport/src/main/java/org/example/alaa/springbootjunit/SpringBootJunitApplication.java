package org.example.alaa.springbootjunit;

import org.example.alaa.springbootjunit.models.CollegeStudent;
import org.example.alaa.springbootjunit.models.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SpringBootJunitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJunitApplication.class, args);
    }

    @Bean
    @Scope("prototype")
    public CollegeStudent getCollegeStudent() {
        System.out.println("Bean Method --> Object created");
        return new CollegeStudent();
    }

}
