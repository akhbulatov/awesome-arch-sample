package com.example.awesomearchsample.data.search.remote.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KtorSearchApiTest {

    @Test
    fun getReposSearchResult_encodesSpecialCharactersAsOneQueryParameter() = runBlocking {
        val query = "c++ & tools#tag"
        val engine = MockEngine { request ->
            assertEquals("/search/repositories", request.url.encodedPath)
            assertEquals(setOf("q"), request.url.parameters.names())
            assertEquals(listOf(query), request.url.parameters.getAll("q"))
            assertEquals("", request.url.fragment)

            respond(
                content = """{"items":[]}""",
                headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            )
        }

        engine.use {
            HttpClient(engine) {
                defaultRequest { url("https://example.com/") }
                install(ContentNegotiation) { json() }
            }.use { client ->
                val result = KtorSearchApi(client).getReposSearchResult(query)

                assertTrue(result.items.isEmpty())
            }
        }
    }
}
