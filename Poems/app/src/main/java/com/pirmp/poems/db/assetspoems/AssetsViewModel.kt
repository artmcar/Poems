package com.pirmp.poems.db.assetspoems

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssetsViewModel(application: Application): AndroidViewModel(application) {

    val getAllPoems: LiveData<List<AssetsDbFields>>
    private val repository: AssetsRepository

    init {
        val assetsDao = AssetsDatabase.getDatabase(application).assetsDao()
        repository = AssetsRepository(assetsDao)
        getAllPoems = repository.getAllPoems
    }

    fun updateField(fields: AssetsDbFields){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateField(fields)
        }
    }
}