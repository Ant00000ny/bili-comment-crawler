package `fun`.fantasea.bilicommentcrawler.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import okhttp3.OkHttpClient

val om: ObjectMapper = ObjectMapper()
    .registerKotlinModule()
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)

val client: OkHttpClient = OkHttpClient.Builder()
    .followRedirects(true)
    .build()
