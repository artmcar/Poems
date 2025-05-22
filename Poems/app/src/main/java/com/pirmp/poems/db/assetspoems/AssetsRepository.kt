package com.pirmp.poems.db.assetspoems

import androidx.lifecycle.LiveData
import com.pirmp.poems.db.DbFields
import com.pirmp.poems.db.PoemDao

class AssetsRepository(private val assetsDao: PoemDao) {
    val getAllPoems : LiveData<List<DbFields>> = assetsDao.getAllPoems()
}