package com.epam.lisovyn.pet.repository.flower

import com.epam.lisovyn.pet.common.model.Flower

interface FlowerRepository {

    fun getFlowers(): List<Flower>
}