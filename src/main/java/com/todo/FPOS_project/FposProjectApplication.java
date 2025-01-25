package com.todo.FPOS_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = "com.todo.FPOS_project")
@EnableMongoRepositories(basePackages = "com.todo.FPOS_project.db.repositories")
@Configuration
@EnableMongoAuditing
@EnableScheduling
@EnableAsync
public class FposProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FposProjectApplication.class, args);
	}

}
