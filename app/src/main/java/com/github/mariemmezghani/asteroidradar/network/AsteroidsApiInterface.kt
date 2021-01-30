package com.github.mariemmezghani.asteroidradar.network

import com.github.mariemmezghani.asteroidradar.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Constants.BASE_URL).build()


interface AsteroidsApiInterface {
    @GET("neo/rest/v1/feed?api_key=${Constants.API_KEY}")
    fun getProperties(): String
}

object AsteroidsApi {
    val retrofitService: AsteroidsApiInterface by lazy {
        retrofit.create(AsteroidsApiInterface::class.java)
    }
}