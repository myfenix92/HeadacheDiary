package com.hfad.headachediary.VM

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hfad.headachediary.DB.HeadacheRepository
import com.hfad.headachediary.Entity.CharacterEntity
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.Entity.MedicinesEntity
import com.hfad.headachediary.Entity.LocalizationEntity
import kotlinx.coroutines.launch

class HeadacheViewModel(private val repository: HeadacheRepository) : ViewModel() {

    val allItems: LiveData<List<HeadacheTuple>> = repository.allItems.asLiveData()

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
class HeadacheViewModelFactory(private val repository: HeadacheRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HeadacheViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HeadacheViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}