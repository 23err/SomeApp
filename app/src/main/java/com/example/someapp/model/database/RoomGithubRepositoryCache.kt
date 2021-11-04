package com.example.someapp.model.database

import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single
import java.lang.RuntimeException

class RoomGithubRepositoryCache(private val db: AppDatabase): IRoomGithubRepositoryCache {
    override fun insertRepositories(user:GithubUser, repositories: List<GithubRepo>) {
        val roomUser = user.login?.let { db.userDao().findByLogin(it) }
            ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubRepository(
                it.id ?: "",
                it.name ?: "",
                it.forks ?: 0,
                roomUser.id
            )
        }
        db.repositoryDao().insert(roomRepos)
    }

    override fun getRepositories(user: GithubUser): Single<List<GithubRepo>> {
        return Single.fromCallable {
            val roomUser = user.login?.let { db.userDao().findByLogin(it) }
                ?: throw RuntimeException("No such user in cache")
            db.repositoryDao().findForUser(roomUser.id)
                .map { GithubRepo(it.id, it.name, it.forksCount) }
        }
    }
}