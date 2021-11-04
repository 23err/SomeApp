package com.example.someapp

import android.app.Application
import androidx.room.Room
import com.example.someapp.model.database.AppDatabase
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router


    val db by lazy {
        AppDatabase.create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set

    }
}