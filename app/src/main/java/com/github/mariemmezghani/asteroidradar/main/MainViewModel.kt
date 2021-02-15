package com.github.mariemmezghani.asteroidradar.main

import android.app.Application
import androidx.lifecycle.*
import com.github.mariemmezghani.asteroidradar.network.AsteroidsApi
import com.github.mariemmezghani.asteroidradar.network.parseAsteroidsJsonResult
import org.json.JSONObject
import com.github.mariemmezghani.asteroidradar.Asteroid
import com.github.mariemmezghani.asteroidradar.PictureOfDay
import com.github.mariemmezghani.asteroidradar.database.AsteroidRoom
import com.github.mariemmezghani.asteroidradar.database.getDatabase
import com.github.mariemmezghani.asteroidradar.network.AsteroidsApiInterface
import com.github.mariemmezghani.asteroidradar.network.ImageApi
import com.github.mariemmezghani.asteroidradar.repository.AsteroidsRepository
import kotlinx.coroutines.launch


class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = AsteroidsRepository(database)
    val asteroids = repository.asteroids
private val _image=MutableLiveData<PictureOfDay>()
    val image:LiveData<PictureOfDay>
    get()=_image
    /**
     * Call getData() on init so we can display status immediately.
     */

    init {
        getData()
        getImageOfTheDay()
    }

    fun getData() {
        viewModelScope.launch {
            repository.refreshAsteroids()

        }
    }

    private fun getImageOfTheDay() {
        viewModelScope.launch {
            try {
                _image.value = ImageApi.retrofitImageService.getImage()
                if (image.value?.mediaType != "image"){
                    _image.value = null
                }

            } catch (e: Exception) {
                println(e.message)
            }
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