package com.webservices.restfulwebservices

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["domain/controller", "config"])
@EntityScan(value = "domain/entity")
@EnableJpaRepositories(value="domain/repository")
class RestfulWebServicesApplication

fun main(args: Array<String>) {
    runApplication<RestfulWebServicesApplication>(*args)

}



