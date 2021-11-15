package com.example.someapp

import android.app.Application
import com.example.someapp.di.AppModule
import com.example.someapp.di.DaggerAppComponent
import com.example.someapp.di.ReposSubcomponent
import com.example.someapp.model.database.AppDatabase

class App : Application() {

    var reposSubcomponent: ReposSubcomponent? = null

    val db by lazy {
        AppDatabase.create(applicationContext)
    }

    val appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun initReposSubcomponent(): ReposSubcomponent {
        return appComponent.reposSubcomponent().also { reposSubcomponent = it }
    }

    fun releaseReposSubcomponent(){
        reposSubcomponent = null
    }

    companion object {
        internal lateinit var instance: App
            private set
    }
}