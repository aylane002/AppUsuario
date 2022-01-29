package com.example.prova2.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.prova2.data.db.entity.UsuarioEntity

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insert(usuario: UsuarioEntity): Long

    @Update
    suspend fun update(usuario: UsuarioEntity)

    @Query("DELETE FROM usuario WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM usuario")
    suspend fun deleteAll()

    @Query("SELECT * FROM usuario")
    suspend fun getAll(): List<UsuarioEntity>
}