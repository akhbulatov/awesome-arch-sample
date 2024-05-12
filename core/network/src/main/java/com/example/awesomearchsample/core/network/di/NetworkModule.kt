package com.example.awesomearchsample.core.network.di

import com.example.awesomearchsample.core.common.util.AppLogger
import com.example.awesomearchsample.core.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClientEngine(): HttpClientEngine {
        return OkHttp.create()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            explicitNulls = false
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(engine: HttpClientEngine, json: Json): HttpClient {
        return HttpClient(engine = engine) {
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
