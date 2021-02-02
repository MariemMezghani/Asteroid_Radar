package com.github.mariemmezghani.asteroidradar.network

import com.github.mariemmezghani.asteroidradar.database.DatabaseAsteroid

data class NetworkAsteroid(
    val id: Long, val codename: String, val closeApproachDate: String,
    val absoluteMagnitude: Double, val estimatedDiameter: Double,
    val relativeVelocity: Double, val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)

fun List<NetworkAsteroid>.asDatabaseModel():Array<DatabaseAsteroid> {
    return map {
        DatabaseAsteroid(
            it.id,
            it.codename,
            it.closeApproachDate,
            it.absoluteMagnitude,
            it.estimatedDiameter,
            it.relativeVelocity,
            it.distanceFromEarth,
            it.isPotentiallyHazardous
        )
    }.toTypedArray()
}
