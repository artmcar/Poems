package com.pirmp.poems.db.assetspoems

import androidx.lifecycle.LiveData
import com.pirmp.poems.db.AssetsPoemDao

class AssetsRepository(private val assetsDao: AssetsPoemDao) {
    val getAllPoems : LiveData<List<AssetsDbFields>> = assetsDao.getAllPoems()

    suspend fun updateField(fields: AssetsDbFields){
        assetsDao.updateField(fields)
    }
}