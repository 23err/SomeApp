package com.example.someapp.model.database

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM user WHERE login=:login LIMIT 1")
    fun findByLogin(login:String): RoomGithubUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users:RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUser>)

    @Update
    fun update(user:RoomGithubUser)

    @Update
    fun update(vararg users:RoomGithubUser)

    @Delete
    fun delete(user:RoomGithubUser)

    @Delete
    fun delete(vararg users:RoomGithubUser)

}