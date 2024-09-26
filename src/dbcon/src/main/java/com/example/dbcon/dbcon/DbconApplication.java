package com.example.dbcon.dbcon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DbconApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbconApplication.class, args);
	}

}
