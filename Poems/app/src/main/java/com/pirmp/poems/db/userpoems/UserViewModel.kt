package com.pirmp.poems.db.userpoems

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    val getAllPoems: LiveData<List<DbFields>>
    private val repository: UserRepository

    init {
        val userDao  = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllPoems = repository.getAllPoems
    }

    fun insertPoem(fields: DbFields){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertPoem(fields)
        }
    }

    fun updateField(fields: DbFields){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateField(fields)
        }
    }

    fun deletePoem(fields: DbFields){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePoem(fields)
        }
    }

    fun deleteAllPoems(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }

}