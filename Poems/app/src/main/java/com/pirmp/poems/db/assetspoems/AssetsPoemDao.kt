package com.pirmp.poems.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.pirmp.poems.db.assetspoems.AssetsDbFields


@Dao
interface AssetsPoemDao {
    @Update
    suspend fun updateField(fields: AssetsDbFields)

    @Query("SELECT * FROM assetspoem ORDER BY id ASC")
    fun getAllPoems(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem WHERE id = :id LIMIT 1")
    suspend fun getPoemById(id: Int): AssetsDbFields


    @Query("SELECT * FROM assetspoem ORDER BY author ASC")
    fun getPoemsByPoetName(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem ORDER BY author DESC")
    fun getPoemsByBackPoetName(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem ORDER BY poem ASC")
    fun getPoemsByPoem(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem ORDER BY poem DESC")
    fun getPoemsByBackPoem(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem ORDER BY fav DESC")
    fun getPoemsByFav(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem ORDER BY fav ASC")
    fun getPoemsByBackFav(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem ORDER BY date ASC")
    fun getPoemsByDateOld(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem ORDER BY date DESC")
    fun getPoemsByDateNew(): LiveData<List<AssetsDbFields>>

}