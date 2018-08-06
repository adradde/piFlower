package com.epam.lisovyn.iflower.repository.api.firebase

import com.epam.lisovyn.iflower.extensions.toMap
import com.epam.lisovyn.iflower.model.domen.Referenced
import com.epam.lisovyn.iflower.model.repository.IdentifiedItem
import com.epam.lisovyn.iflower.model.repository.UniquenessItem
import com.epam.lisovyn.iflower.repository.api.annotation.Reference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.experimental.runBlocking
import org.codehaus.jackson.annotate.JsonIgnore
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.lang.IllegalArgumentException

@Component
class FireBaseApiAccessor(private val firebaseDatabase: FirebaseDatabase) {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    val monitor = java.lang.Object()

    internal fun <T : IdentifiedItem> preHit(vararg references: Class<T>) {
        logger.debug("starting pre hit")
        references.forEach { getAll(it) }
    }

    internal fun <T : IdentifiedItem> getAll(referenceClass: Class<T>) = runBlocking<List<T>> {
        obtainDataSnapshot(referenceClass).run {
            children.map { it.getValue(referenceClass).apply { id = it.key } }
        }
    }

    private fun <T : Referenced> obtainDataSnapshot(referenceClass: Class<T>, id: String? = null): DataSnapshot {
        lateinit var dataSnapshot: DataSnapshot

        val reference = "${referenceClass.getReference()}${id?.let { "/$it" }.orEmpty()}"
        firebaseDatabase.getReference(reference)
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
            return dataSnapshot
        }
    }

    internal fun <T : UniquenessItem> get(referenceClass: Class<T>): T? = runBlocking {
        obtainDataSnapshot(referenceClass, UniquenessItem.id)
                .getValue(referenceClass)
    }

    internal fun <T : IdentifiedItem> get(referenceClass: Class<T>, id: String) = runBlocking {
        obtainDataSnapshot(referenceClass, id)
                .let {
                    it.getValue(referenceClass)
                            ?.apply {
                                this.id = id
                            }
                }
    }

    fun update(item: IdentifiedItem) {
        update("${item::class.java.getReference()}/${item.id}", item)
    }

    fun update(item: UniquenessItem) {
        update("${item::class.java.getReference()}/${UniquenessItem.id}", item)
    }

    private fun update(reference: String, item: Any) {
        firebaseDatabase.getReference(reference)
                .updateChildrenAsync(item.toMap())
    }
}

private fun <T> Class<T>.getReference() = getAnnotation(Reference::class.java)?.value ?: throw IllegalArgumentException("Wrong class")
private fun Any.toMap(): Map<String, Any> = javaClass.declaredFields
        .filter { it.isAnnotationPresent(JsonIgnore::class.java).not() }
        .also { it.forEach { it.isAccessible = true } }
        .map { it.name to it.get(this) }.toMap()
