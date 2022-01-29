package com.example.prova2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prova2.data.db.dao.UsuarioDao
import com.example.prova2.data.db.entity.UsuarioEntity

@Database(entities = [UsuarioEntity:: class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val usuarioDao: UsuarioDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }

                return instance
            }
        }
    }


}