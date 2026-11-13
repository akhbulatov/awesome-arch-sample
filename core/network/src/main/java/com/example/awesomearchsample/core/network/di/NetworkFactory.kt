package com.example.awesomearchsample.core.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.awesomearchsample.core.commonapi.util.AppLogger
import com.example.awesomearchsample.core.network.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkFactory(
    context: Context,
    private val json: Json,
    private val baseApiUrl: String
) {

    val httpClient: HttpClient by lazy {
        HttpClient(OkHttp) {
            engine {
                addInterceptor(ChuckerInterceptor(context.applicationContext))
            }
            expectSuccess = true
            defaultRequest {
                url(urlString = baseApiUrl)
            }
            install(plugin = HttpTimeout) {
                connectTimeoutMillis = 15_000
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
