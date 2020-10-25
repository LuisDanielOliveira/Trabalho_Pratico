package com.example.trabalhopraticoparte3.db

import androidx.lifecycle.LiveData
import com.example.trabalhopraticoparte3.dao.CityDao
import com.example.trabalhopraticoparte3.entities.City


class CityRepository(private val cityDao: CityDao) {

    val allCities: LiveData<List<City>> = cityDao.getAlphabetizedCities()
    suspend fun insert(city: City){
        cityDao.insert(city)
    }

}