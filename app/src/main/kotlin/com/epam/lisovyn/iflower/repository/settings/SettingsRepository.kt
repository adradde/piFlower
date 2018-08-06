package com.epam.lisovyn.iflower.repository.settings

import com.epam.lisovyn.iflower.model.domen.AppSettings

interface SettingsRepository {

    fun getSettings(): AppSettings?
    fun updateSettings(appSettings: AppSettings)
}