package com.hfad.headachediary.DB

import androidx.annotation.WorkerThread
import com.hfad.headachediary.Entity.CharacterEntity
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.Entity.MedicinesEntity
import com.hfad.headachediary.Entity.LocalizationEntity
import kotlinx.coroutines.flow.Flow

class HeadacheRepository(private val headacheDao: HeadacheDao) {
    val allItems: Flow<List<HeadacheTuple>> = headacheDao.getAllItems()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertItem(headacheEntity: HeadacheEntity): Long {
        return headacheDao.insertNewItem(headacheEntity)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertLocalization(localizationEntity: LocalizationEntity) {
        headacheDao.insertNewLocalization(localizationEntity)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertCharacter(characterEntity: CharacterEntity) {
        headacheDao.insertNewCharacter(characterEntity)
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMedicines(medicinesEntity: MedicinesEntity) {
        headacheDao.insertNewMedicines(medicinesEntity)
    }
}