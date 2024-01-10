package com.hfad.headachediary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "headache_item",
    indices = [Index("id")])
data class HeadacheEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "date_item") val dateItem: Long,
    @ColumnInfo(name = "duration") val duration: Int
)
