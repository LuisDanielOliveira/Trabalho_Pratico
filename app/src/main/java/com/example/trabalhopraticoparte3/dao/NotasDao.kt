package com.example.trabalhopraticoparte3.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.trabalhopraticoparte3.entities.NotasEntity

@Dao
interface NotasDao {

    @Query("SELECT * from notas_table ORDER BY titulo ASC")
    fun getAllNotas(): LiveData<List<NotasEntity>>

    @Query("SELECT * FROM notas_table WHERE notas == :notas")
    fun getTituloByNotas(notas: String): LiveData<List<NotasEntity>>

    @Query("SELECT * FROM notas_table WHERE titulo == :titulo")
    fun getNotasFromTitulo(titulo: String): LiveData<NotasEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notasEntity: NotasEntity)

    @Update
    suspend fun updateNota(notasEntity: NotasEntity)

    @Query("DELETE FROM notas_table")
    suspend fun deleteAll()

    @Query("DELETE FROM notas_table where titulo == :titulo")
    suspend fun deleteByTitulo(titulo: String)

    @Query("UPDATE notas_table SET notas=:notas WHERE titulo == :titulo")
    suspend fun updateNotaFromTitulo(titulo: String, notas: String)
}