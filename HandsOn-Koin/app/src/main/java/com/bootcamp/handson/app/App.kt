package com.bootcamp.handson.app

import android.app.Application
import com.bootcamp.handson.di.repositoryModule
import com.bootcamp.handson.di.serviceModule
import com.bootcamp.handson.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(repositoryModule, serviceModule, viewModelModule))
        }
    }
}