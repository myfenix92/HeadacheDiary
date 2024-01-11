package com.hfad.headachediary

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HeadacheDao {
    @Query("SELECT headache_item.id," +
            "headache_item.date_item," +
            "headache_item.duration, localization.localization_item," +
            "character.character_item," +
            "medicines.medicines_name, medicines.medicines_dose, medicines.medicines_count " +
            "FROM headache_item, localization, character, medicines " +
            "WHERE headache_item.id = localization.id_item AND " +
            "headache_item.id = character.id_item AND " +
            "headache_item.id = medicines.id_item")
    fun getAllItems(): LiveData<List<HeadacheTuple>>

    @Insert
    suspend fun insertNewItem(headacheEntity: HeadacheEntity)

    @Insert
    suspend fun insertNewLocalization(localizationEntity: LocalizationEntity)

    @Insert
    suspend fun insertNewCharacter(characterEntity: CharacterEntity)

    @Insert
    suspend fun insertNewMedicines(medicinesEntity: MedicinesEntity)
}