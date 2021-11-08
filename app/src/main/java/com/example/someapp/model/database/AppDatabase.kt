package com.example.someapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.lang.RuntimeException

@Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun repositoryDao(): RepositoryDao

    companion object {
        const val DB_NAME = "someapp.db"
        private var instance: AppDatabase? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?): AppDatabase {
            instance = instance ?: Room.databaseBuilder(
                context!!,
                AppDatabase::class.java,
                DB_NAME
            ).build()
            return instance!!
        }
    }
}