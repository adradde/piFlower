package com.epam.lisovyn.pet.repository.flower

import com.epam.lisovyn.pet.common.model.Flower
import com.epam.lisovyn.pet.repository.api.firebase.FireBaseApi
import org.springframework.stereotype.Component

@Component
class FlowerRepositoryImpl(val fireBaseApi: FireBaseApi) : FlowerRepository {

    override fun getFlowers(): List<Flower> {
        return fireBaseApi.getAllFlowers()
    }
}