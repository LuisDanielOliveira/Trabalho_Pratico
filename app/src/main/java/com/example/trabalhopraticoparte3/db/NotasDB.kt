package com.example.trabalhopraticoparte3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.trabalhopraticoparte3.dao.NotasDao
import com.example.trabalhopraticoparte3.entities.NotasEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch



@Database(entities = arrayOf(NotasEntity::class), version = 8, exportSchema = false)
public abstract class NotasDB : RoomDatabase() {

    abstract fun notasDao(): NotasDao

    private class WordDatabaseCallback(
            private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var notasDao = database.notasDao()

                    // Delete all content here.
                    notasDao.deleteAll()

                    // Add sample cities.
                    var notas = NotasEntity(1, "Algo", "algo")
                    notasDao.insert(notas)
                    notas = NotasEntity(2, "Nsei", "nsei")
                    notasDao.insert(notas)
                    notas = NotasEntity(3, "Qualquer coisa", "qualquer coisa")
                    notasDao.insert(notas)

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotasDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotasDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, NotasDB::class.java, "notas_database")
                        //estratégia de destruição
                        .fallbackToDestructiveMigration()
                        .addCallback(WordDatabaseCallback(scope))
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}