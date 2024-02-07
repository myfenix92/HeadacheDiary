package com.hfad.headachediary.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.hfad.headachediary.Entity.HeadacheEntity
import org.jetbrains.annotations.Nullable

@Entity(
    tableName = "medicines",
    indices = [Index(value = ["id", "medicines_name"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = HeadacheEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_item"]
        )
    ])
data class MedicinesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "id_item") val idItem: Long,
    @ColumnInfo(name = "medicines_name") val medicinesName: String?,
//    @ColumnInfo(name = "medicines_dose") val medicinesDose: Int?,
//    @ColumnInfo(name = "medicines_count") val medicinesCount: Int?
) {
    constructor(idItem: Long, medicinesName: String?)//, medicinesDose: Int?, medicinesCount: Int?)
            : this(0, idItem, medicinesName)//, medicinesDose, medicinesCount)
}
