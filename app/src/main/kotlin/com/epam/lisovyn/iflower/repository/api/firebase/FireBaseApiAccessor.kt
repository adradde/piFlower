package com.epam.lisovyn.iflower.repository.api.firebase

import com.epam.lisovyn.iflower.common.model.IdentifiedItem
import com.epam.lisovyn.iflower.repository.api.annotation.Reference
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

    internal fun <T: IdentifiedItem> preHit(vararg references: Class<T>) {
        logger.debug("starting pre hit")
        references.forEach { getAll(it) }
    }


    internal fun <T: IdentifiedItem> getAll(referenceClass: Class<T>) = runBlocking<List<T>> {
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
            dataSnapshot.children.map { it.getValue(referenceClass).apply { id = it.key } }
        }
    }

    internal fun <T : IdentifiedItem> get(referenceClass: Class<T>, id: String) = runBlocking {
        lateinit var dataSnapshot: DataSnapshot

        firebaseDatabase.getReference("${referenceClass.getReference()}/$id")
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
            dataSnapshot.getValue(referenceClass)
                    ?.apply {
                        this.id = id
                    }
        }
    }

    override fun update(item: IdentifiedItem) {
        firebaseDatabase.getReference("${item::class.java.getReference()}/${item.id}")
                .updateChildrenAsync(mapOf("lastUpdate" to System.currentTimeMillis()))
    }
}

private fun <T> Class<T>.getReference() = getAnnotation(Reference::class.java)?.value ?: throw IllegalArgumentException("Wrong class")
