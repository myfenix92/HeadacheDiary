package com.hfad.headachediary.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hfad.headachediary.Entity.CharacterEntity
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.MedicinesEntity
import com.hfad.headachediary.Entity.LocalizationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    version = 1,
    exportSchema = false,
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
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { db ->
                scope.launch {
                    var headacheDao = db.getHeadacheDao()
                    var headacheEntity = HeadacheEntity(1, 1709, 4)
                    headacheDao.insertNewItem(headacheEntity)
                    var localizationEntity = LocalizationEntity(1, 1, "temporal")
                    headacheDao.insertNewLocalization(localizationEntity)
                    var localizationEntity1 = LocalizationEntity(2, 1, "radial")
                    headacheDao.insertNewLocalization(localizationEntity1)
                    var characterEntity = CharacterEntity(1, 1, "sharped")
                    headacheDao.insertNewCharacter(characterEntity)
                    var medicinesEntity = MedicinesEntity(1, 1, "analgin", 30, 2)
                    headacheDao.insertNewMedicines(medicinesEntity)

                    var headacheEntity2 = HeadacheEntity(2, 1745, 43)
                    headacheDao.insertNewItem(headacheEntity2)
                    var localizationEntity2 = LocalizationEntity(3, 2, "temporal12")
                    headacheDao.insertNewLocalization(localizationEntity2)
                    var localizationEntity12 = LocalizationEntity(4, 2, "radial12")
                    headacheDao.insertNewLocalization(localizationEntity12)
                    var characterEntity2 = CharacterEntity(2, 2, "strong")
                    headacheDao.insertNewCharacter(characterEntity2)
                    var medicinesEntity2 = MedicinesEntity(2, 2, "ketorax", 300, 1)
                    headacheDao.insertNewMedicines(medicinesEntity2)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HeadacheDatabase? = null

        fun getDB(context: Context, scope: CoroutineScope): HeadacheDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeadacheDatabase::class.java,
                    "db-6"
                ).addCallback(HeadacheDBCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}