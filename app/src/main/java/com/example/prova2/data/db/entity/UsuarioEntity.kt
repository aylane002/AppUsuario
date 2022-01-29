package com.example.prova2.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuario")
data class UsuarioEntity (
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val name: String,
        val email: String
    )
