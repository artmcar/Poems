package com.pirmp.poems.db.assetspoems

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class AssetsViewModel(application: Application): AndroidViewModel(application) {

    val getAllPoems: LiveData<List<AssetsDbFields>>
    private val repository: AssetsRepository

    init {
        val assetsDao = AssetsDatabase.getDatabase(application).assetsDao()
        repository = AssetsRepository(assetsDao)
        getAllPoems = repository.getAllPoems
    }
}