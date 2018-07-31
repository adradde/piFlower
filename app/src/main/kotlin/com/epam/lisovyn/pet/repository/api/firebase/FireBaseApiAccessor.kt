package com.epam.lisovyn.pet.repository.api.firebase

import com.epam.lisovyn.pet.repository.api.annotation.Reference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
abstract class FireBaseApiAccessor(val firebaseDatabase: FirebaseDatabase) : FireBaseApi {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    val monitor = java.lang.Object()

    internal fun <T> preHit(vararg references: Class<T>) {
        logger.debug("starting pre hit")
        references.forEach { get(it) }
    }


    internal fun <T> get(referenceClass: Class<T>) = runBlocking<List<T>> {
        lateinit var dataSnapshot: DataSnapshot

        firebaseDatabase.getReference(referenceClass.getReference())
                .addValueEventListener(object : ValueEventListener {

                    override fun onCancelled(error: DatabaseError) {
                        throw error.toException()
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        synchronized(monitor) {
                            dataSnapshot = snapshot
                            monitor.notifyAll()
                        }
                    }
                })

        synchronized(monitor) {
            monitor.wait()
            dataSnapshot.children.map { it.getValue(referenceClass) }
        }
    }
}

private fun <T> Class<T>.getReference() = getAnnotation(Reference::class.java)?.value ?: throw IllegalArgumentException("Wrong class")
