package com.example.someapp.model


import com.example.someapp.presenter.GithubRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GithubUsersRepo(val api: IDataSource) : IGithubUserRepo{
    override fun getUsers() = api.loadUsers().subscribeOn(Schedulers.io())
    override fun getUser(login: String)= api.loadUser(login).subscribeOn(Schedulers.io())
    override fun getRepos(url: String) = api.loadRepos(url).subscribeOn(Schedulers.io())
}