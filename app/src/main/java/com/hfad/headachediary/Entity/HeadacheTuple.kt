package com.hfad.headachediary.Entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation

data class HeadacheTuple(
    @Embedded val item: HeadacheEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id_item"
    )
    val localizationList: List<LocalizationEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id_item"
    )
    val characterList: List<CharacterEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id_item"
    )
    val medicinesList: List<MedicinesEntity>,

    @Relation(
        parentColumn = "id",
        entityColumn = "id_item"
    )
    val medicinesDoseList: List<MedicinesDoseEntity>
)
