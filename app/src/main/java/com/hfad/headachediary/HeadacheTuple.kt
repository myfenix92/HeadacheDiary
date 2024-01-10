package com.hfad.headachediary

import androidx.room.ColumnInfo

data class HeadacheTuple(
    val id: Long,
    @ColumnInfo(name = "date_item") val dateItem: Long,
    @ColumnInfo(name = "duration") val duration: String,
    @ColumnInfo(name = "localization_item") val localizationItem: String,
    @ColumnInfo(name = "character_item") val characterItem: String,
    @ColumnInfo(name = "medicines_name") val medicinesName: String,
    @ColumnInfo(name = "medicines_dose") val medicinesDose: String,
    @ColumnInfo(name = "medicines_count") val medicinesCount: String
)
