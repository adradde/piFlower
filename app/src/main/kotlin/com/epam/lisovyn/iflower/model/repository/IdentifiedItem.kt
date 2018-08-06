package com.epam.lisovyn.iflower.model.repository

import com.epam.lisovyn.iflower.model.domen.Referenced

abstract class IdentifiedItem(open var id: String = ""): Referenced