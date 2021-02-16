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
    // The internal MutableLiveData String that stores the most recent response status
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the status String
    val status: LiveData<String>
        get() = _status

    private val database = getDatabase(application)
    private val repository = AsteroidsRepository(database)
    val asteroids = repository.asteroids
    val asteroidsOfToday = repository.asteroidsOfToday
    val savedAsteroids = repository.savedAsteroids
    private val _image = MutableLiveData<PictureOfDay>()
    val image: LiveData<PictureOfDay>
        get() = _image

    /**
     * Call getData() on init so we can display status immediately.
     */

    init {
        getData()
        //getImageOfTheDay()
    }

    fun getData() {
        viewModelScope.launch {
            try {
                repository.refreshAsteroids()
                _status.value = "Success"
                _image.value = ImageApi.retrofitImageService.getImage()

                if (image.value?.mediaType != "image") {
                    _image.value = null
                }
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }
        }
    }

    private fun getImageOfTheDay() {
        viewModelScope.launch {
            try {


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