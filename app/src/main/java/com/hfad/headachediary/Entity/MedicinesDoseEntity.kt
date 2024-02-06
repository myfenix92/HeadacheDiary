package com.hfad.headachediary.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "medicines_dose",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = MedicinesEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_item"]
        )
    ])
data class MedicinesDoseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "id_item") val idItem: Long,
    @ColumnInfo(name = "medicines_dose") val medicinesDose: Int?,
    @ColumnInfo(name = "medicines_count") val medicinesCount: Int?
) {
    constructor(idItem: Long, medicinesDose: Int?, medicinesCount: Int?)
            : this(0, idItem, medicinesDose, medicinesCount)
}
