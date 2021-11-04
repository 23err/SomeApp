package com.example.someapp.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class RoomGithubUser(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "login") var login: String?,
    @ColumnInfo(name = "avatar_url") var avatarUrl: String?,
    @ColumnInfo(name = "repo_url",) var repoUrl: String?,
)
