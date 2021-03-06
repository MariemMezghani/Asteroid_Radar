package com.github.mariemmezghani.asteroidradar.worker

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.mariemmezghani.asteroidradar.database.getDatabase
import com.github.mariemmezghani.asteroidradar.main.MainViewModel
import com.github.mariemmezghani.asteroidradar.repository.AsteroidsRepository
import retrofit2.HttpException

class RefreshDataWorker(appContext: Context, params:WorkerParameters):CoroutineWorker(appContext, params) {
    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }
    override suspend fun doWork(): Result {
        val database= getDatabase(applicationContext)
        val repository= AsteroidsRepository(database)

        return try {
            repository.refreshAsteroids()
            return Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}