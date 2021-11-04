package com.example.someapp.model.database

import androidx.room.*

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: RoomGithubRepository)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repositories: List<RoomGithubRepository>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg repositories: RoomGithubRepository)

    @Update
    fun update(repository: RoomGithubRepository)

    @Update
    fun update(vararg repositories: RoomGithubRepository)

    @Delete
    fun delete(repository: RoomGithubRepository)

    @Delete
    fun delete(vararg repositories: RoomGithubRepository)

    @Query("SELECT * FROM repository")
    fun getAll(): List<RoomGithubRepository>

    @Query("SELECT * FROM repository WHERE user_id = :userId")
    fun findForUser(userId: String) : List<RoomGithubRepository>
}