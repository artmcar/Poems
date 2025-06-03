package com.pirmp.poems.db.assetspoems

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssetsViewModel(application: Application): AndroidViewModel(application) {

    val getAllPoems: LiveData<List<AssetsDbFields>>
    val getPoemsByPoem: LiveData<List<AssetsDbFields>>
    val getPoemsByPoetName: LiveData<List<AssetsDbFields>>
    val getPoemsByFav: LiveData<List<AssetsDbFields>>
    val getPoemsByDateOld: LiveData<List<AssetsDbFields>>
    val getPoemsByDateNew: LiveData<List<AssetsDbFields>>
    val getPoemsByBackFav: LiveData<List<AssetsDbFields>>
    val getPoemsByBackPoem: LiveData<List<AssetsDbFields>>
    val getPoemsByBackPoetName: LiveData<List<AssetsDbFields>>
    private val repository: AssetsRepository

    init {
        val assetsDao = AssetsDatabase.getDatabase(application).assetsDao()
        repository = AssetsRepository(assetsDao)
        getAllPoems = repository.getAllPoems
        getPoemsByPoem = repository.getPoemsByPoem
        getPoemsByPoetName = repository.getPoemsByPoetName
        getPoemsByFav = repository.getPoemsByFav
        getPoemsByDateOld = repository.getPoemsByDateOld
        getPoemsByDateNew = repository.getPoemsByDateNew
        getPoemsByBackFav = repository.getPoemsByBackFav
        getPoemsByBackPoem = repository.getPoemsByBackPoem
        getPoemsByBackPoetName = repository.getPoemsByBackPoetName
    }

    fun updateField(fields: AssetsDbFields){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateField(fields)
        }
    }
}