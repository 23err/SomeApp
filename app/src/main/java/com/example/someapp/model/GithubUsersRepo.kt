package com.example.someapp.model

import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo : IGithubUserRepo{
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    override fun getUsers() = RetrofitGithub.api.loadUsers().subscribeOn(Schedulers.io())

    override fun getUser(login: String)= RetrofitGithub.api.loadUser(login).subscribeOn(Schedulers.io())


    fun getUser(index: Int): Observable<GithubUser> {
        return Observable.create {
            it.onNext(repositories[index])
            it.onComplete()
        }
    }
}