package com.github.mariemmezghani.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseAsteroid::class], version = 1, exportSchema = false)
abstract class AsteroidRoom : RoomDatabase() {

    abstract val dao: AsteroidDAO

    companion object {

        @Volatile
        private var INSTANCE: AsteroidRoom? = null

        fun getInstance(context: Context): AsteroidRoom {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AsteroidRoom::class.java,
                        "asteroids"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
