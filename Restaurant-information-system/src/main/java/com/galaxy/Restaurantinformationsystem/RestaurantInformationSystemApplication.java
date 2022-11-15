package com.galaxy.Restaurantinformationsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
public class RestaurantInformationSystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(RestaurantInformationSystemApplication.class, args);
	}

}
