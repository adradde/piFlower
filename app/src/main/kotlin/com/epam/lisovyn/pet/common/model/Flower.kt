package com.epam.lisovyn.pet.common.model

import com.epam.lisovyn.pet.repository.api.annotation.Reference

@Reference("/flowers")
data class Flower(override var id: String = "",
                  var name: String = "",
                  var color: String = "",
                  var lastUpdate: Long = 0) : IdentifiedItem(id)