package com.epam.lisovyn.iflower.repository.settings

import com.epam.lisovyn.iflower.model.domen.AppSettings
import com.epam.lisovyn.iflower.repository.api.settings.SettingsApi
import com.epam.lisovyn.iflower.settings.StoredAppSettings
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class SettingsRepositoryImpl(private val settingsApi: SettingsApi,
                             private val storedAppSettings: StoredAppSettings) : SettingsRepository {

    @PostConstruct
    fun init(){
        if (getSettings() == null){
            settingsApi.updateSettings(AppSettings.from(storedAppSettings))
        }
    }

    override fun getSettings(): AppSettings? = settingsApi.getSettings()

    override fun updateSettings(appSettings: AppSettings) {
        settingsApi.updateSettings(appSettings)
    }
}