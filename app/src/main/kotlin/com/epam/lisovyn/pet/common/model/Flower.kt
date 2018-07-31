package com.epam.lisovyn.pet.common.model

import com.epam.lisovyn.pet.repository.api.annotation.Reference

@Reference("/flowers")
data class Flower(var id: String = "", var name: String = "", var color: String = "")