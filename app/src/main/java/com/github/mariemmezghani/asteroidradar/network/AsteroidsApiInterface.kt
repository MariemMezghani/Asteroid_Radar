package com.github.mariemmezghani.asteroidradar.network

import androidx.lifecycle.LiveData
import com.github.mariemmezghani.asteroidradar.Constants
import com.github.mariemmezghani.asteroidradar.PictureOfDay
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Constants.BASE_URL).build()

private val retrofit2 = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.BASE_URL).build()

interface AsteroidsApiInterface {
    @GET("neo/rest/v1/feed?api_key=${Constants.API_KEY}")
    suspend fun getAsteroids(): String
}

object AsteroidsApi {
    val retrofitService: AsteroidsApiInterface by lazy {
        retrofit.create(AsteroidsApiInterface::class.java)
    }
}

interface ImageApiInterface {
    @GET("planetary/apod?api_key=${Constants.API_KEY}")
    suspend fun getImage(): PictureOfDay
}

object ImageApi {
    val retrofitImageService: ImageApiInterface by lazy {
        retrofit2.create(ImageApiInterface::class.java)
    }
}


