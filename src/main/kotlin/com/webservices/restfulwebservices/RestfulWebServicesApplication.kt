package com.webservices.restfulwebservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["controller", "config"])
@EntityScan(value = "entity")
@EnableJpaRepositories(value="repository")
class RestfulWebServicesApplication

fun main(args: Array<String>) {
    runApplication<RestfulWebServicesApplication>(*args)

}



