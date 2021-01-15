package com.github.mariemmezghani.asteroidradar.api

import com.github.mariemmezghani.asteroidradar.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(Constants.BASE_URL).build()

interface AsteroidsApiInterface {
    @GET("neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=${Constants.API_KEY}")
    fun getProperties(): Call<String>
}

object AsteroidsApi {
    val retrofitservice: AsteroidsApiInterface by lazy {
        retrofit.create(AsteroidsApiInterface::class.java)
    }
}
