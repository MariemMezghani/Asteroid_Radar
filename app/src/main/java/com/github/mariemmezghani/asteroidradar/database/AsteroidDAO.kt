package com.github.mariemmezghani.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AsteroidDAO {
    // get all asteroids from today onwards
    @Query("select * from asteroid_table where closeApproachDate >= date('now') order by closeApproachDate")
    fun getAllAsteroidsFromToday(): LiveData<List<DatabaseAsteroid>>

    // get all saved asteroids
    @Query("select * from asteroid_table order by closeApproachDate")
    fun getAllAsteroids(): LiveData<List<DatabaseAsteroid>>

    //get all asteroids of today
    @Query("select * from asteroid_table where closeApproachDate == date('now') order by closeApproachDate")
    fun getAllAsteroidsOfToday(): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg asteroids: DatabaseAsteroid)
}