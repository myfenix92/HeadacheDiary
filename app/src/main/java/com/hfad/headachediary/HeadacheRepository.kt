package com.hfad.headachediary

import androidx.lifecycle.LiveData

class HeadacheRepository(private val headacheDao: HeadacheDao) {
    val allItems: LiveData<List<HeadacheTuple>> = headacheDao.getAllItems()

    suspend fun insertItem(headacheEntity: HeadacheEntity) {
        headacheDao.insertNewItem(headacheEntity)
    }

    suspend fun insertLocalization(localizationEntity: LocalizationEntity) {
        headacheDao.insertNewLocalization(localizationEntity)
    }

    suspend fun insertCharacter(characterEntity: CharacterEntity) {
        headacheDao.insertNewCharacter(characterEntity)
    }

    suspend fun insertMedicines(medicinesEntity: MedicinesEntity) {
        headacheDao.insertNewMedicines(medicinesEntity)
    }
}