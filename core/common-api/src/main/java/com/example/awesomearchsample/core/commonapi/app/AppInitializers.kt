package com.example.awesomearchsample.core.commonapi.app

class AppInitializers(
    private val initializers: Set<AppInitializer>
) : AppInitializer {

    override fun init() {
        initializers.forEach { it.init() }
    }
}
