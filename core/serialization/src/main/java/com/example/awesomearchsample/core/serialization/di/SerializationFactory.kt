package com.example.awesomearchsample.core.serialization.di

import kotlinx.serialization.json.Json

class SerializationFactory {

    val json: Json by lazy {
        Json {
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
        }
    }
}
