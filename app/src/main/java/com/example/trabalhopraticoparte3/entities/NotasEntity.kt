package com.example.trabalhopraticoparte3.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas_table")

class NotasEntity(
        // Int? = null so when creating instance id has not to be passed as argument

        @PrimaryKey(autoGenerate = true) val id: Int? = null,
        @ColumnInfo(name = "titulo") val titulo: String,
        @ColumnInfo(name = "notas") val notas: String
)

