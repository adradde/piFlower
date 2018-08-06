package com.epam.lisovyn.iflower.model.domen

import com.epam.lisovyn.iflower.model.repository.UniquenessItem
import com.epam.lisovyn.iflower.repository.api.annotation.Reference
import com.epam.lisovyn.iflower.settings.StoredAppSettings

@Reference("/settings")
data class AppSettings(val uiUpdateFrequency: Long = 0) : UniquenessItem {
    companion object {
        fun from(storedAppSettings: StoredAppSettings) =
                AppSettings(uiUpdateFrequency = storedAppSettings.uiUpdateFrequency)
    }
}