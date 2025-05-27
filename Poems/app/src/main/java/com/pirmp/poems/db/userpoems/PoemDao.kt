package com.pirmp.poems.db.userpoems

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
    fun getPoemsByPoetName(): LiveData<List<DbFields>>

    @Query("SELECT * FROM userpoem ORDER BY poem ASC")
    fun getPoemsByPoem(): LiveData<List<DbFields>>

    @Query("SELECT * FROM userpoem ORDER BY id ASC")
    fun getAllPoems(): LiveData<List<DbFields>>

    @Query("SELECT * FROM userpoem WHERE id = :id")
    suspend fun getPoemById(id: Int): DbFields

    @Query("UPDATE userpoem SET fav = :isFav WHERE id = :poemId")
    suspend fun setFav(poemId: Int, isFav: Int)
}