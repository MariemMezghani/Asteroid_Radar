package com.github.mariemmezghani.asteroidradar.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.mariemmezghani.asteroidradar.Asteroid
import com.github.mariemmezghani.asteroidradar.database.AsteroidRoom
import com.github.mariemmezghani.asteroidradar.database.asDomainModel
import com.github.mariemmezghani.asteroidradar.network.AsteroidsApi
import com.github.mariemmezghani.asteroidradar.network.asDatabaseModel
import com.github.mariemmezghani.asteroidradar.network.parseAsteroidsJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AsteroidsRepository(private val database: AsteroidRoom) {
    /**
     * list of asteroids that will be displayed
     */
    val asteroids: LiveData<List<Asteroid>> = Transformations.map(
        database.dao.getAllAsteroids()
    ) { it.asDomainModel() }

    suspend fun refreshAsteroids() {
            withContext(Dispatchers.IO) {
                val result = AsteroidsApi.retrofitService.getAsteroids()
                val json = JSONObject(result)
                val asteroids =
                    parseAsteroidsJsonResult(json)
                database.dao.insert(*asteroids.asDatabaseModel())
        }
    }
}