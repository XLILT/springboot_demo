package com.xl.www.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class Application {

	public static void main(String[] args) {
        log.info("1, {}", Thread.currentThread().getId());
		SpringApplication.run(Application.class, args);
        log.info("2, {}", Thread.currentThread().getId());
	}

}
