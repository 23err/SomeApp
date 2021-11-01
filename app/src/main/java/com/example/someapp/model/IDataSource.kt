package com.example.someapp.model

import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSource {
    @GET("users")
    fun loadUsers(): Single<List<GithubUser>>

    @GET("users/{login}")
    fun loadUser(@Path("login") login: String): Single<GithubUser>

    @GET()
    fun loadRepos(@Url url: String): Single<List<GithubRepo>>
}