package com.pirmp.poems.db.userpoems

import androidx.lifecycle.LiveData


class UserRepository(private val userDao: PoemDao) {

    val getAllPoems : LiveData<List<DbFields>> = userDao.getAllPoems()

    val getPoemsByPoem: LiveData<List<DbFields>> = userDao.getPoemsByPoem()

    val getPoemsByPoetName: LiveData<List<DbFields>> = userDao.getPoemsByPoetName()

    val getPoemsByFav: LiveData<List<DbFields>> = userDao.getPoemsByFav()

    val getPoemsByDateOld: LiveData<List<DbFields>> = userDao.getPoemsByDateOld()

    val getPoemsByDateNew: LiveData<List<DbFields>> = userDao.getPoemsByDateNew()

    val getPoemsByBackFav: LiveData<List<DbFields>> = userDao.getPoemsByBackFav()

    val getPoemsByBackPoem: LiveData<List<DbFields>> = userDao.getPoemsByBackPoem()

    val getPoemsByBackPoetName: LiveData<List<DbFields>> = userDao.getPoemsByBackPoetName()

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