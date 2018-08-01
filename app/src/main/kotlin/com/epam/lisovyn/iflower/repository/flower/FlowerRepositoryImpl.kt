package com.epam.lisovyn.iflower.repository.flower

import com.epam.lisovyn.iflower.app.socket.WebSocketConfiguration
import com.epam.lisovyn.iflower.common.model.Flower
import com.epam.lisovyn.iflower.repository.api.firebase.FireBaseApi
import org.slf4j.LoggerFactory
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class FlowerRepositoryImpl(val fireBaseApi: FireBaseApi,
                           val websocket: SimpMessagingTemplate) : FlowerRepository {

    val logger = LoggerFactory.getLogger(javaClass)

    @Scheduled(fixedDelayString = "\${app.update.frequency}")
    private fun pushUpdate() {
        logger.debug("Send update to repository")
        getFlowers().forEach {
            fireBaseApi.update(it)
            websocket.convertAndSend("${WebSocketConfiguration.MESSAGE_PREFIX}/updateFlower/", it)
        }


    }

    override fun updateFlower(id: String): Flower? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFlowers(): List<Flower> = fireBaseApi.getAllFlowers()

    override fun getFlower(id: String): Flower? = fireBaseApi.getFlower(id)
}