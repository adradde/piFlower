package com.epam.lisovyn.iflower.model.domen

import com.epam.lisovyn.iflower.model.repository.IdentifiedItem
import com.epam.lisovyn.iflower.repository.api.annotation.Reference

@Reference("/flowers")
data class Flower(override var id: String = "",
                  var name: String = "",
                  var category: String = "",
                  var color: String = "",
                  var state: State? = null,
                  var lastUpdate: Long = 0) : IdentifiedItem(id)

data class State(var wetLevel: Int = 0,
                 var unit: String = "%")