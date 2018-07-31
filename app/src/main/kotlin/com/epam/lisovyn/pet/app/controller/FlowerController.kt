package com.epam.lisovyn.pet.app.controller

import com.epam.lisovyn.pet.repository.flower.FlowerRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/flowers")
class FlowerController(val flowerRepository: FlowerRepository) {

    @GetMapping
    fun home()  = flowerRepository.getFlowers()
}

data class Response(val body: String)