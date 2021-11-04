package com.example.someapp.model.database

import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single

interface IRoomGithubUserCache {
    fun insertUsers(users: List<GithubUser>)
    fun getAll(): Single<List<GithubUser>>
}