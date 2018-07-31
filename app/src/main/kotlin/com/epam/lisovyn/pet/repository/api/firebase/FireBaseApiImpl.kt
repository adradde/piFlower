package com.epam.lisovyn.pet.repository.api.firebase

import com.epam.lisovyn.pet.common.model.Flower
import com.google.firebase.database.FirebaseDatabase
import org.springframework.stereotype.Component

@Component
class FireBaseApiImpl(firebaseDatabase: FirebaseDatabase) : FireBaseApiAccessor(firebaseDatabase) {
    init {
        preHit(Flower::class.java)
    }

    override fun getAllFlowers(): List<Flower> = getAll(Flower::class.java)

    override fun getFlower(id: String): Flower? = get(Flower::class.java, id)
}