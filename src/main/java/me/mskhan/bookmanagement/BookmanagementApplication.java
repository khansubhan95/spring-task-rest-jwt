package me.mskhan.bookmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class BookmanagementApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BookmanagementApplication.class, args);
	}

}
