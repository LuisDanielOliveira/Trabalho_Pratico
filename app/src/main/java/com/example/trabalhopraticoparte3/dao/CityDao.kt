package com.example.trabalhopraticoparte3.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trabalhopraticoparte3.entities.City


@Dao
interface CityDao {

    @Query("SELECT * from city_table ORDER BY city ASC")
    fun getAlphabetizedCities(): LiveData<List<City>>

    @Query("SELECT * from city_table WHERE capital == :name")
    fun getCitiesFromCountry(name: String): LiveData<List<City>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Query( "DELETE FROM city_table")
    suspend fun deleteAll()
}