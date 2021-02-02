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
    private val asteroids = repository.asteroids

    // The internal MutableLiveData that stores the List of Asteroids
    private val _response = MutableLiveData<List<Asteroid>>()

    // The external immutable LiveData for the most recent request of Asteroid list
    val response: LiveData<List<Asteroid>>
        get() = _response

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val status: LiveData<String>
        get() = _status

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

}