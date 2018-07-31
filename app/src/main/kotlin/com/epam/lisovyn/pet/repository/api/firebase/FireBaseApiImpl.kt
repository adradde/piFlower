package com.epam.lisovyn.pet.repository.api.firebase

import com.epam.lisovyn.pet.common.model.Flower
import com.google.firebase.database.FirebaseDatabase
import org.springframework.stereotype.Component

@Component
class FireBaseApiImpl(firebaseDatabase: FirebaseDatabase) : FireBaseApiAccessor(firebaseDatabase) {

    init {
        preHit(Flower::class.java)
    }

    override fun getAllFlowers(): List<Flower> = get(Flower::class.java)
}