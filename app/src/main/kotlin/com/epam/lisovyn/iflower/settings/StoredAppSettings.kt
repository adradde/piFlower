package com.epam.lisovyn.iflower.settings

import com.epam.lisovyn.iflower.model.repository.UniquenessItem
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class StoredAppSettings : UniquenessItem {

    @Value("\${app.update.frequency}")
    var uiUpdateFrequency: Long = 0
}