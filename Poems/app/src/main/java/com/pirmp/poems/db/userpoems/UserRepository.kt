package com.pirmp.poems.db.userpoems

import androidx.lifecycle.LiveData
import com.pirmp.poems.db.DbFields
import com.pirmp.poems.db.PoemDao

class UserRepository(private val userDao: PoemDao) {

    val getAllPoems : LiveData<List<DbFields>> = userDao.getAllPoems()

    suspend fun insertPoem(fields: DbFields){
        userDao.insertPoem(fields)
    }
}