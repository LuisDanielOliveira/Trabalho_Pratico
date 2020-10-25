package com.example.trabalhopraticoparte3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.trabalhopraticoparte3.dao.CityDao
import com.example.trabalhopraticoparte3.entities.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(City::class), version = 1, exportSchema = false)
public abstract class CityDB : RoomDatabase() {

    abstract fun cityDao(): CityDao

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var cityDao = database.cityDao()

                    // Delete all content here.
                    cityDao.deleteAll()

                    //Add sample words
                    var city = City(1, "Viana do Castelo", "Portugal")
                    cityDao.insert(city)
                    city = City(2,"Porto","Portugal")
                    cityDao.insert(city)

                }
            }
        }
    }

    companion object {
    @Volatile
    private var INSTANCE: CityDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CityDB{
        val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, CityDB::class.java, "cities_database")

                        //estratégia de destrução
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                    INSTANCE = instance
                return instance
            }
        }
    }
}



