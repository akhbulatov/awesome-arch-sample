package com.example.awesomearchsample.core.network.di

import com.example.awesomearchsample.core.common.util.AppLogger
import com.example.awesomearchsample.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkFactory(
    private val json: Json
) {

    private val httpClientEngine: HttpClientEngine by lazy {
        OkHttp.create()
    }

    val httpClient: HttpClient by lazy {
        HttpClient(engine = httpClientEngine) {
            expectSuccess = true
            defaultRequest {
                url(urlString = "https://api.github.com/")
            }
            install(plugin = ContentNegotiation) {
                json(json = json)
            }
            install(plugin = Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        AppLogger.d(message)
                    }
                }
                level = if (BuildConfig.DEBUG) LogLevel.BODY else LogLevel.NONE
            }
        }
    }

}
