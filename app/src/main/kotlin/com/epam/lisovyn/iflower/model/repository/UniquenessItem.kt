package com.epam.lisovyn.iflower.model.repository

import com.epam.lisovyn.iflower.model.domen.Referenced

interface UniquenessItem : Referenced {
    companion object {
        val id: String = "uniq_0"
    }
}