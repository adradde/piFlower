package com.epam.lisovyn.iflower.repository.api.flower

import com.epam.lisovyn.iflower.model.domen.Flower
import com.epam.lisovyn.iflower.repository.api.firebase.FireBaseApiAccessor
import org.springframework.stereotype.Component

@Component
class DefaultFlowerApi(private val fireBaseApiAccessor: FireBaseApiAccessor): FlowerApi {
    init {
        fireBaseApiAccessor.preHit(Flower::class.java)
    }

    override fun getAllFlowers(): List<Flower> = fireBaseApiAccessor.getAll(Flower::class.java)

    override fun getFlower(id: String): Flower? = fireBaseApiAccessor.get(Flower::class.java, id)
}