package kr.co.hrmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HumanResourceManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HumanResourceManagerApplication.class, args);
    }
}