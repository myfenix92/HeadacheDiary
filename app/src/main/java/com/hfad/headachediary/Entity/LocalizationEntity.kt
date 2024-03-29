package com.hfad.headachediary.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.hfad.headachediary.Entity.HeadacheEntity

@Entity(
    tableName = "localization",
    indices = [Index("id")],
    foreignKeys = [
        ForeignKey(
            entity = HeadacheEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_item"]
        )
    ])

data class LocalizationEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "id_item") val idItem: Long,
    @ColumnInfo(name = "localization_item") val localizationItem: String
) {
    constructor(idItem: Long, localizationItem: String) : this(0, idItem, localizationItem)
}
