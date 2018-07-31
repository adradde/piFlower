package com.epam.lisovyn.pet.repository.api.firebase

import com.google.auth.oauth2.GoogleCredentials
import com.google.common.io.Resources
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.FirebaseDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class FirebaseApiConfiguration(@Value("\${firebase.key.path}") val firebaseKey: String,
                                    @Value("\${firebase.url}") val firebaseUrl: String) {

    @Bean
    open fun createFirebaseDatabase(): FirebaseDatabase {

        val serviceAccount = Resources.getResource(firebaseKey).openStream()
        val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl(firebaseUrl)
                .build()

        FirebaseApp.initializeApp(options)
        return FirebaseDatabase.getInstance()
    }
}