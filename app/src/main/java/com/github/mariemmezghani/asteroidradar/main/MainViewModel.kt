package com.github.mariemmezghani.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.github.mariemmezghani.asteroidradar.network.AsteroidsApi
import com.github.mariemmezghani.asteroidradar.network.parseAsteroidsJsonResult
import org.json.JSONObject
import com.github.mariemmezghani.asteroidradar.Asteroid
import com.github.mariemmezghani.asteroidradar.database.AsteroidRoom
import com.github.mariemmezghani.asteroidradar.database.getDatabase
import com.github.mariemmezghani.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = AsteroidsRepository(database)
    val asteroids = repository.asteroids

    /**
     * Call getData() on init so we can display status immediately.
     */
    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            repository.refreshAsteroids()

        }
    }

    private val _navigateToSelectedAsteroid = MutableLiveData<Asteroid>()
    val navigateToSelectedAsteroid: LiveData<Asteroid>
        get() = _navigateToSelectedAsteroid

    fun displayAsteroidDetails(asteroid: Asteroid) {
        _navigateToSelectedAsteroid.value = asteroid
    }

    fun displayAsteroidDetailsComplete() {
        _navigateToSelectedAsteroid.value = null
    }

}