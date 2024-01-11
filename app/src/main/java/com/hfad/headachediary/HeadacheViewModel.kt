package com.hfad.headachediary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HeadacheViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: HeadacheRepository
    val  allItems: LiveData<List<HeadacheTuple>>

    init {
        val headacheDao = HeadacheDatabase.getDB(application, viewModelScope).getHeadacheDao()
        repository = HeadacheRepository(headacheDao)
        allItems = repository.allItems
    }

    fun insertItem(headacheEntity: HeadacheEntity) = viewModelScope.launch {
        repository.insertItem(headacheEntity)
    }

    fun insertLocalization(localizationEntity: LocalizationEntity) = viewModelScope.launch {
        repository.insertLocalization(localizationEntity)
    }

    fun insertCharacter(characterEntity: CharacterEntity) = viewModelScope.launch {
        repository.insertCharacter(characterEntity)
    }

    fun insertMedicines(medicinesEntity: MedicinesEntity) = viewModelScope.launch {
        repository.insertMedicines(medicinesEntity)
    }
}