package com.epam.lisovyn.iflower.common.model

import com.epam.lisovyn.iflower.repository.api.annotation.Reference

@Reference("/flowers")
data class Flower(override var id: String = "",
                  var name: String = "",
                  var color: String = "",
                  var lastUpdate: Long = 0) : IdentifiedItem(id)