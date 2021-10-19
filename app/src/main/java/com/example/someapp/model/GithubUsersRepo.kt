package com.example.someapp.model

import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Observable

class GithubUsersRepo {
    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    fun getUsers() : Observable<List<GithubUser>> {
        return Observable.just(repositories)
    }

    fun getUser(index: Int): Observable<GithubUser> {
        return Observable.create {
            it.onNext(repositories[index])
            it.onComplete()
        }
    }
}