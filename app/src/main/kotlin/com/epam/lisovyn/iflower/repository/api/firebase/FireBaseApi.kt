package com.epam.lisovyn.iflower.repository.api.firebase

import com.epam.lisovyn.iflower.common.model.Flower
import com.epam.lisovyn.iflower.common.model.IdentifiedItem

interface FireBaseApi {

    fun getAllFlowers(): List<Flower>
    fun update(item: IdentifiedItem)

    fun getFlower(id: String): Flower?
}