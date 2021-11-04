package com.example.someapp.model.database

import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single

interface IRoomGithubRepositoryCache {
    fun insertRepositories(user:GithubUser, repositories: List<GithubRepo>)
    fun getRepositories(user:GithubUser):Single<List<GithubRepo>>
}