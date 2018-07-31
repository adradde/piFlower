package com.epam.lisovyn.pet.repository.api.firebase

import com.epam.lisovyn.pet.common.model.Flower
import com.epam.lisovyn.pet.common.model.IdentifiedItem

interface FireBaseApi {

    fun getAllFlowers(): List<Flower>
    fun update(item: IdentifiedItem)

    fun getFlower(id: String): Flower?
}