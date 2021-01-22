package com.github.mariemmezghani.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.mariemmezghani.asteroidradar.Asteroid
import com.github.mariemmezghani.asteroidradar.api.AsteroidsApi
import com.github.mariemmezghani.asteroidradar.api.parseAsteroidsJsonResult
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

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
                _response.value = parseAsteroidsJsonResult(json).get(0).codename
            } catch (e: Exception) {
                _response.value = "Failure: " + e.message

            }

        }
    }
}