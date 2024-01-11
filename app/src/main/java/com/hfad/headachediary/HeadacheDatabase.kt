package com.hfad.headachediary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        HeadacheEntity::class,
        LocalizationEntity::class,
        CharacterEntity::class,
        MedicinesEntity::class,
    ]
)
abstract class HeadacheDatabase : RoomDatabase() {
    abstract fun getHeadacheDao(): HeadacheDao

    private class HeadacheDBCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { db ->
                scope.launch {
                    var headacheDao = db.getHeadacheDao()

                    var headacheEntity = HeadacheEntity(1, 1709, 4)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HeadacheDatabase? = null

        fun getDB(context: Context, scope: CoroutineScope): HeadacheDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeadacheDatabase::class.java,
                    "headache_diary"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}