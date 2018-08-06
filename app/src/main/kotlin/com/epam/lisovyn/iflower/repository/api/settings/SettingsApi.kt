package com.epam.lisovyn.iflower.repository.api.settings

import com.epam.lisovyn.iflower.model.domen.AppSettings

interface SettingsApi {

    fun getSettings(): AppSettings?
    fun updateSettings(appSettings: AppSettings)
}