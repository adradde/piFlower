package com.epam.lisovyn.iflower.repository.api.settings

import com.epam.lisovyn.iflower.model.domen.AppSettings
import com.epam.lisovyn.iflower.repository.api.firebase.FireBaseApiAccessor
import org.springframework.stereotype.Component

@Component
class DefaultSettingsApi(private val fireBaseApiAccessor: FireBaseApiAccessor): SettingsApi {

    override fun getSettings(): AppSettings? = fireBaseApiAccessor.get(AppSettings::class.java)

    override fun updateSettings(appSettings: AppSettings) = fireBaseApiAccessor.update(appSettings)
}