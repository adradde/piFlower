package com.epam.lisovyn.iflower.repository.flower

import com.epam.lisovyn.iflower.model.domen.Flower

interface FlowerRepository {

    fun getFlowers(): List<Flower>
    fun getFlower(id: String): Flower?
    fun updateFlower(id: String): Flower?
}