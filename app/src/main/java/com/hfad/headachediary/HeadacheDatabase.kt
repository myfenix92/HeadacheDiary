package com.hfad.headachediary

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
        HeadacheEntity::class,
        LocalizationEntity::class,
        CharacterEntity::class,
        MedicinesEntity::class,
    ]
)
abstract class HeadacheDatabase : RoomDatabase() {
    abstract fun getHeadacheDao(): HeadacheDao
}