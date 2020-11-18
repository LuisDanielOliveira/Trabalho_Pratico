package com.example.trabalhopraticoparte3.api

data class User(

        val id: Int,
        val nome: String,
        val password: String
)

data class pontos(

        val id: Int,
        val descricao: String,
        val tipo: String,
        val latitude: String,
        val lonitude: String

)