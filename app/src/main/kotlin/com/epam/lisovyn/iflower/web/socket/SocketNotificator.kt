package com.epam.lisovyn.iflower.web.socket

import com.epam.lisovyn.iflower.model.domen.Flower
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component


@Component
class SocketNotificator(val template: SimpMessagingTemplate) {

    fun sendUpdate(flowers: List<Flower>) {
        println("sent flowers update: $flowers")
        template.convertAndSend("/topic/update/flowers", flowers)
    }
}