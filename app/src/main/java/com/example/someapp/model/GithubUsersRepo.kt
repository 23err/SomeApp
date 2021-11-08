package com.example.someapp.model


import com.example.someapp.INetworkStatus
import com.example.someapp.model.database.AppDatabase
import com.example.someapp.model.database.IRoomGithubUserCache
import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(
    val api: IDataSource,
    val networkStatus: INetworkStatus,
    val roomUsersCache: IRoomGithubUserCache
) :
    IGithubUserRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.loadUsers()
                .flatMap { users ->
                    roomUsersCache.insertUsers(users)
                    Single.just(users)
                }
        } else {
            roomUsersCache.getAll()
        }
    }.subscribeOn(Schedulers.io())

    override fun getUser(login: String) = api.loadUser(login).subscribeOn(Schedulers.io())
}