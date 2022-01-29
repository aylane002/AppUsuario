package com.example.prova2.repository

import com.example.prova2.data.db.dao.UsuarioDao
import com.example.prova2.data.db.entity.UsuarioEntity

class DatabaseDataSource(
    private val usuarioDao : UsuarioDao
) :UsuarioRepository {

    override suspend fun insertUsuario(name: String, email: String): Long {
        val usuario = UsuarioEntity(
            name = name,
            email = email
        )

        return usuarioDao.insert(usuario)
    }

    override suspend fun updateUsuario(id: Long, name: String, email: String) {
        val usuario = UsuarioEntity(
            id = id,
            name = name,
            email = email
        )

        usuarioDao.update(usuario)
    }

    override suspend fun deleteUsuario(id: Long) {
        usuarioDao.delete(id)
    }

    override suspend fun deleteAllUsuario() {
        usuarioDao.deleteAll()
    }

    override suspend fun getAllSubscribers(): List<UsuarioEntity> {
       return usuarioDao.getAll()
    }
}