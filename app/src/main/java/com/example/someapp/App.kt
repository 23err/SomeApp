package com.example.someapp

import android.app.Application
import com.example.someapp.di.AppModule
import com.example.someapp.di.DaggerAppComponent
import com.example.someapp.model.database.AppDatabase

class App : Application() {

    val db by lazy {
        AppDatabase.create(applicationContext)
    }

    val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        internal lateinit var instance: App
            private set
    }
}