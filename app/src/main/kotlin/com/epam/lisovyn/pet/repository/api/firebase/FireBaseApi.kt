package com.epam.lisovyn.pet.repository.api.firebase

import com.epam.lisovyn.pet.common.model.Flower

interface FireBaseApi {

    fun getAllFlowers(): List<Flower>
}