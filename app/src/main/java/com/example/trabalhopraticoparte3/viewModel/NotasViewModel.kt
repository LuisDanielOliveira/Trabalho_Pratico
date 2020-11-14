package com.example.trabalhopraticoparte3.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.trabalhopraticoparte3.db.NotasRepository
import com.example.trabalhopraticoparte3.db.NotasDB
import com.example.trabalhopraticoparte3.entities.NotasEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotasViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotasRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNotas: LiveData<List<NotasEntity>>

    init {
        val notasDao = NotasDB.getDatabase(application, viewModelScope).notasDao()
        repository = NotasRepository(notasDao)
        allNotas = repository.allNotas
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(notasEntity: NotasEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notasEntity)
    }

    // delete all
    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    // delete by city
    fun deleteByTitulo(titulo: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteByTitulo(titulo)
    }

    fun getTituloByNotas(notas: String): LiveData<List<NotasEntity>> {
        return repository.getTituloByNotas(notas)
    }

    fun getNotasFromTitulo(titulo: String): LiveData<NotasEntity> {
        return repository.getNotasFromTitulo(titulo)
    }

    fun updateNota(notasEntity: NotasEntity) = viewModelScope.launch {
        repository.updateNota(notasEntity)
    }

    fun updateNotaFromTitulo(titulo: String, notas: String) = viewModelScope.launch {
        repository.updateNotaFromTitulo(titulo, notas)
    }
}