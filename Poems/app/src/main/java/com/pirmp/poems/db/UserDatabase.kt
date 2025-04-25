package com.pirmp.poems.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DbFields::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun dao(): PoemDao
}