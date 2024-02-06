package com.hfad.headachediary.DB

import androidx.annotation.WorkerThread
import com.hfad.headachediary.Entity.CharacterEntity
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.Entity.MedicinesEntity
import com.hfad.headachediary.Entity.LocalizationEntity
import com.hfad.headachediary.Entity.MedicinesDoseEntity
import kotlinx.coroutines.flow.Flow

class HeadacheRepository(private val headacheDao: HeadacheDao) {
    val allItems: Flow<List<HeadacheTuple>> = headacheDao.getAllItems()
    val allMedicines: Flow<List<MedicinesEntity>> = headacheDao.getAllMedicines()
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
    suspend fun insertMedicines(medicinesEntity: MedicinesEntity): Long {
        return headacheDao.insertNewMedicines(medicinesEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertMedicinesDose(medicineDoseEntity: MedicinesDoseEntity) {
        headacheDao.insertNewMedicinesDose(medicineDoseEntity)
    }

    @WorkerThread
    fun updateItem(headacheEntity: HeadacheEntity) {
        headacheDao.updateItem(headacheEntity)
    }

    @WorkerThread
    fun updateLocalization(localizationEntity: LocalizationEntity) {
        headacheDao.updateLocalization(localizationEntity)
    }

    @WorkerThread
    fun updateCharacter(characterEntity: CharacterEntity) {
        headacheDao.updateCharacter(characterEntity)
    }

    @WorkerThread
    fun updateMedicines(medicinesEntity: MedicinesEntity) {
        headacheDao.updateMedicines(medicinesEntity)
    }

    @WorkerThread
    fun updateMedicinesDose(medicinesDoseEntity: MedicinesDoseEntity) {
        headacheDao.updateMedicinesDose(medicinesDoseEntity)
    }
}