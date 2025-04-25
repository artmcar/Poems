package com.pirmp.poems.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PoemDao {
    @Insert
    suspend fun insertPoem(fields: DbFields)

    @Delete
    suspend fun deletePoem(fields: DbFields)

    @Query("SELECT * FROM userpoem ORDER BY author ASC")
    fun getPoemsByPoetName(): List<DbFields>

    @Query("SELECT * FROM userpoem ORDER BY poem ASC")
    fun getPoemsByPoem(): List<DbFields>

    @Query("SELECT * FROM userpoem ORDER BY id ASC")
    fun getAllPoems(): LiveData<List<DbFields>>
}