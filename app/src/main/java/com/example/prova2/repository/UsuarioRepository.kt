package com.example.prova2.repository

import com.example.prova2.data.db.entity.UsuarioEntity

interface UsuarioRepository {

    suspend fun insertUsuario(name: String, email: String): Long

    suspend fun updateUsuario(id: Long, name: String, email: String)

    suspend fun deleteUsuario(id: Long)

    suspend fun deleteAllUsuario()

    suspend fun getAllSubscribers(): List<UsuarioEntity>
}