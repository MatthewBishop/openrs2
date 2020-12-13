package org.openrs2.json

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import javax.inject.Inject
import javax.inject.Provider

public class ObjectMapperProvider @Inject constructor(
    private val modules: Set<Module>
) : Provider<ObjectMapper> {
    override fun get(): ObjectMapper {
        return ObjectMapper()
            .registerKotlinModule()
            .registerModules(modules)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    }
}
