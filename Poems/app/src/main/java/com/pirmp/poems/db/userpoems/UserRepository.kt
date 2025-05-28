package com.pirmp.poems.db.userpoems

import androidx.lifecycle.LiveData


class UserRepository(private val userDao: PoemDao) {

    val getAllPoems : LiveData<List<DbFields>> = userDao.getAllPoems()

    suspend fun insertPoem(fields: DbFields){
        userDao.insertPoem(fields)
    }

    suspend fun updateField(fields: DbFields){
        userDao.updateField(fields)
    }

    suspend fun deletePoem(fields: DbFields){
        userDao.deletePoem(fields)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllPoems()
    }
}