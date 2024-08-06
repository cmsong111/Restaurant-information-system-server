package com.galaxy.restaurant.information.system

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class RestaurantInformationSystemApplication

fun main(args: Array<String>) {
    runApplication<RestaurantInformationSystemApplication>(*args)
}
