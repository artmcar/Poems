package com.pirmp.poems.db.assetspoems

import androidx.lifecycle.LiveData
import com.pirmp.poems.db.AssetsPoemDao

class AssetsRepository(private val assetsDao: AssetsPoemDao) {
    val getAllPoems : LiveData<List<AssetsDbFields>> = assetsDao.getAllPoems()

    val getPoemsByPoem: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByPoem()

    val getPoemsByPoetName: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByPoetName()

    val getPoemsByFav: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByFav()

    val getPoemsByDateOld: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByDateOld()

    val getPoemsByDateNew: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByDateNew()

    val getPoemsByBackFav: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByBackFav()

    val getPoemsByBackPoem: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByBackPoem()

    val getPoemsByBackPoetName: LiveData<List<AssetsDbFields>> = assetsDao.getPoemsByBackPoetName()

    suspend fun updateField(fields: AssetsDbFields){
        assetsDao.updateField(fields)
    }
}