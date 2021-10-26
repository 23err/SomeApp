package com.example.someapp.model

import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUserRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUser(login:String):Single<GithubUser>
}