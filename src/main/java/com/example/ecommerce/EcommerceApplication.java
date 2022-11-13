package com.example.ecommerce;

import com.example.ecommerce.Model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean(name = "userBean")
	public User getUser() {
		User user = new User();
		user.setPassword("123456");
		user.setEmail("temp@gmail.com");
		return user;
	}

}
