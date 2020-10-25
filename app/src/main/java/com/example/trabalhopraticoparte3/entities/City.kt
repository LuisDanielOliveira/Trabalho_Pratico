package com.example.trabalhopraticoparte3.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo



@Entity (tableName = "city_table")

class City(

        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        @ColumnInfo(name = "city") val city: String,
        @ColumnInfo(name = "capital") val capital: String


)


