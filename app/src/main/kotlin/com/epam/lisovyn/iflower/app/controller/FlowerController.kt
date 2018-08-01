package com.epam.lisovyn.iflower.app.controller

import com.epam.lisovyn.iflower.repository.flower.FlowerRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/flowers")
class FlowerController(val flowerRepository: FlowerRepository) {

    @GetMapping
    @CrossOrigin(origins = ["http://localhost:3000"])
    fun getFlowers() = flowerRepository.getFlowers()


    @GetMapping("/{id}")
    fun getFlower(@PathVariable id: String) = flowerRepository.getFlower(id)
}

data class Response(val body: String)