package com.cursoandroid.arch

import android.app.Application
import com.cursoandroid.arch.di.dbModule
import com.cursoandroid.arch.di.repositoryModule
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication: Application() {
    @InternalCoroutinesApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(listOf(dbModule, repositoryModule))
        }
    }
}