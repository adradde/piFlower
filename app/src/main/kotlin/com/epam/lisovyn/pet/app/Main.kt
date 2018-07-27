package com.epam.lisovyn.pet.app

import com.epam.lisovyn.hard.RaspManager
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
class Main {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            RaspManager()
            println("main class")
        }
    }
}