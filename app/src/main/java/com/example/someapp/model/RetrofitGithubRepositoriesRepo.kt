package com.example.someapp.model

import com.example.someapp.INetworkStatus
import com.example.someapp.model.database.AppDatabase
import com.example.someapp.model.database.IRoomGithubRepositoryCache
import com.example.someapp.model.database.RoomGithubRepository
import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.RuntimeException

class RetrofitGithubRepositoriesRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val roomRepositoryCache: IRoomGithubRepositoryCache
) : IRepositoriesRepo {

    override fun getRepositories(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl?.let { url ->
                    api.loadRepos(url)
                        .flatMap { repositories ->
                            roomRepositoryCache.insertRepositories(user, repositories)
                            Single.fromCallable {
                                repositories
                            }
                        }
                } ?: Single.error<List<GithubRepo>>(RuntimeException("User has no repos url"))
                    .subscribeOn(
                        Schedulers.io()
                    )
            } else {
                roomRepositoryCache.getRepositories(user)
            }
        }.subscribeOn(Schedulers.io())
}