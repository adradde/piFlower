package com.epam.lisovyn.iflower.repository.flower

import com.epam.lisovyn.iflower.model.domen.Flower
import com.epam.lisovyn.iflower.repository.api.flower.FlowerApi
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class FlowerRepositoryImpl(val flowerApi: FlowerApi) : FlowerRepository {

    val logger = LoggerFactory.getLogger(javaClass)

    override fun updateFlower(id: String): Flower? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFlowers(): List<Flower> = flowerApi.getAllFlowers()

    override fun getFlower(id: String): Flower? = flowerApi.getFlower(id)
}