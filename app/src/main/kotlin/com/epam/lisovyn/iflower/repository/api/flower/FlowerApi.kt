package com.epam.lisovyn.iflower.repository.api.flower

import com.epam.lisovyn.iflower.model.domen.Flower

interface FlowerApi {

    fun getAllFlowers(): List<Flower>
    fun getFlower(id: String): Flower?
}