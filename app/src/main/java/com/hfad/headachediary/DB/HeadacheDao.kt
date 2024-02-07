package com.hfad.headachediary.DB

import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.hfad.headachediary.Entity.CharacterEntity
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.Entity.MedicinesEntity
import com.hfad.headachediary.Entity.LocalizationEntity
import com.hfad.headachediary.Entity.MedicinesDoseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeadacheDao {
    @Transaction
    @Query(
        "SELECT * FROM headache_item"
    )
    fun getAllItems(): Flow<List<HeadacheTuple>>

    @Query(
        "SELECT * FROM medicines"
    )
    fun getAllMedicines(): Flow<List<MedicinesEntity>>

    @Insert
    suspend fun insertNewItem(headacheEntity: HeadacheEntity): Long

    @Insert
    suspend fun insertNewLocalization(localizationEntity: LocalizationEntity)

    @Insert
    suspend fun insertNewCharacter(characterEntity: CharacterEntity)

    @Insert
    suspend fun insertNewMedicines(medicinesEntity: MedicinesEntity): Long

    @Insert
    suspend fun insertNewMedicinesDose(medicinesDoseEntity: MedicinesDoseEntity)

    @Update
    fun updateItem(headacheEntity: HeadacheEntity)

    @Update
    fun updateLocalization(localizationEntity: LocalizationEntity)

    @Update
    fun updateCharacter(characterEntity: CharacterEntity)

    @Update
    fun updateMedicines(medicinesEntity: MedicinesEntity)

    @Update
    fun updateMedicinesDose(medicinesDoseEntity: MedicinesDoseEntity)
}