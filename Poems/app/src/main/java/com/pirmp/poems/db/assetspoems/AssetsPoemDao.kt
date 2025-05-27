package com.pirmp.poems.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.pirmp.poems.db.assetspoems.AssetsDbFields

@Dao
interface AssetsPoemDao {
    @Query("SELECT * FROM assetspoem ORDER BY id ASC")
    fun getAllPoems(): LiveData<List<AssetsDbFields>>

    @Query("SELECT * FROM assetspoem WHERE id = :id LIMIT 1")
    suspend fun getPoemById(id: Int): AssetsDbFields

    @Query("UPDATE assetspoem SET fav = :isFav WHERE id = :poemId")
    suspend fun setFav(poemId: Int, isFav: Int)
}