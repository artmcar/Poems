package com.pirmp.poems.db.assetspoems

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pirmp.poems.db.AssetsPoemDao

@Database(entities = [AssetsDbFields::class], version = 5, exportSchema = false)
abstract class AssetsDatabase : RoomDatabase(){
    abstract fun assetsDao(): AssetsPoemDao

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
                val dbFile = context.getDatabasePath("assetspoem")

                val builder = if (!dbFile.exists()){
                    Room.databaseBuilder(context.applicationContext,
                        AssetsDatabase::class.java, "assetspoem").
                            createFromAsset("assetspoem.db")
                }else{
                    Room.databaseBuilder(
                        context.applicationContext,
                        AssetsDatabase::class.java, "assetspoem"
                    )
                }
                val instance = builder.fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}