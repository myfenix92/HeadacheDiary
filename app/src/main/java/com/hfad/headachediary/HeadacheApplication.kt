package com.hfad.headachediary

import android.app.Application
import com.hfad.headachediary.DB.HeadacheDatabase
import com.hfad.headachediary.DB.HeadacheRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HeadacheApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val db by lazy { HeadacheDatabase.getDB(this, applicationScope) }
    val repository by lazy { HeadacheRepository(db.getHeadacheDao()) }
}