package com.epam.lisovyn.pet.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["com.epam.lisovyn.pet"])
@SpringBootApplication
@EnableAutoConfiguration
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication(Application::class.java).run(*args)
        }
    }
}