package com.pirmp.poems.db.assetspoems

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pirmp.poems.db.DbFields
import com.pirmp.poems.db.PoemDao

@Database(entities = [DbFields::class], version = 1, exportSchema = false)
abstract class AssetsDatabase : RoomDatabase(){
    abstract fun assetsDao(): PoemDao

    //Часть паттерна singleton для создания и получения экземпляра базы данных с использованием Room
    companion object{
        @Volatile
        private var INSTANCE: AssetsDatabase? = null

        fun getDatabase(context: Context): AssetsDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AssetsDatabase::class.java,
                    "assetspoem_database"
                ).createFromAsset("assetspoem_database.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}