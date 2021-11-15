package com.example.someapp.model

import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.GithubUser
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single

interface IRepositoriesRepo {
fun getRepositories(user: GithubUser): Single<List<GithubRepo>>
}
