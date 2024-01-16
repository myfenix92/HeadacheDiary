package com.hfad.headachediary.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.hfad.headachediary.Entity.CharacterEntity
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.Entity.MedicinesEntity
import com.hfad.headachediary.Entity.LocalizationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeadacheDao {
    @Transaction
    @Query(
        "SELECT * FROM headache_item"
    )
    fun getAllItems(): Flow<List<HeadacheTuple>>

    @Insert
    suspend fun insertNewItem(headacheEntity: HeadacheEntity)

    @Insert
    suspend fun insertNewLocalization(localizationEntity: LocalizationEntity)

    @Insert
    suspend fun insertNewCharacter(characterEntity: CharacterEntity)

    @Insert
    suspend fun insertNewMedicines(medicinesEntity: MedicinesEntity)
}