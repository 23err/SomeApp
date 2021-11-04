package com.example.someapp.model.database

import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single

class RoomGithubUserCache(
    val db: AppDatabase
) : IRoomGithubUserCache {
    override fun insertUsers(users: List<GithubUser>) {

        val roomUsers = users.map { user ->
            RoomGithubUser(user.id ?: "", user.login, user.avatarUrl, user.reposUrl)
        }
        db.userDao().insert(roomUsers)

    }

    override fun getAll(): Single<List<GithubUser>> {
        return Single.fromCallable {
            db.userDao().getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.repoUrl)
            }
        }
    }

}