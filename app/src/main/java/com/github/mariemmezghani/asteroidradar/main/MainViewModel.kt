package com.github.mariemmezghani.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mariemmezghani.asteroidradar.network.AsteroidsApi
import com.github.mariemmezghani.asteroidradar.network.parseAsteroidsJsonResult
import org.json.JSONObject
import androidx.lifecycle.viewModelScope
import com.github.mariemmezghani.asteroidradar.Asteroid
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    // The internal MutableLiveData that stores the List of Asteroids
    private val _response = MutableLiveData<List<Asteroid>>()

    // The external immutable LiveData for the most recent request of Asteroid list
    val response: LiveData<List<Asteroid>>
        get() = _response

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val status : LiveData<String>
        get()= _status

    /**
     * Call getData() on init so we can display status immediately.
     */
    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                var result = AsteroidsApi.retrofitService.getProperties()
                val json = JSONObject(result)
                _response.value = parseAsteroidsJsonResult(json)
            } catch (e: Exception) {
                _status.value = "Failure: " + e.message

            }

        }
    }
}