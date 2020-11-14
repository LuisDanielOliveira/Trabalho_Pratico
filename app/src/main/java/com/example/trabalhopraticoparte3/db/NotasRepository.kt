package com.example.trabalhopraticoparte3.db


import androidx.lifecycle.LiveData
import com.example.trabalhopraticoparte3.dao.NotasDao
import com.example.trabalhopraticoparte3.entities.NotasEntity

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NotasRepository(private val notasDao: NotasDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNotas: LiveData<List<NotasEntity>> = notasDao.getAllNotas()

    fun getTituloByNotas(notas: String): LiveData<List<NotasEntity>> {
        return notasDao.getTituloByNotas(notas)
    }

    fun getNotasFromTitulo(titulo: String): LiveData<NotasEntity> {
        return notasDao.getNotasFromTitulo(titulo)
    }

    suspend fun insert(notasEntity: NotasEntity) {
        notasDao.insert(notasEntity)
    }

    suspend fun deleteAll(){
        notasDao.deleteAll()
    }

    suspend fun deleteByTitulo(titulo: String){
        notasDao.deleteByTitulo(titulo)
    }

    suspend fun updateNota(notasEntity: NotasEntity) {
        notasDao.updateNota(notasEntity)
    }

    suspend fun updateNotaFromTitulo(titulo: String, notas: String){
        notasDao.updateNotaFromTitulo(titulo, notas)
    }
}