package com.epam.lisovyn.iflower

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.scheduling.annotation.EnableScheduling

@ComponentScan(basePackages = ["com.epam.lisovyn.iflower"])
@PropertySource("classpath:settings.properties")
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
open class Application {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication(Application::class.java).run(*args)
        }
    }
}