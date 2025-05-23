package com.pirmp.poems.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface AssetsPoemDao {
    @Query("SELECT * FROM assetspoem ORDER BY id ASC")
    fun getAllPoems(): LiveData<List<AssetsDbFields>>
}