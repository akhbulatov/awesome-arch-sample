package com.example.awesomearchsample.core.common.app

class AppInitializers(
    private val initializers: Set<AppInitializer>
) : AppInitializer {

    override fun init() {
        initializers.forEach { it.init() }
    }
}
