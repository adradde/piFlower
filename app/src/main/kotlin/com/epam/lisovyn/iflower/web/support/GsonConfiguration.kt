package com.epam.lisovyn.iflower.web.support

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class GsonConfiguration {

    @Bean
    open fun createGson(): Gson = GsonBuilder().setPrettyPrinting().create()
}