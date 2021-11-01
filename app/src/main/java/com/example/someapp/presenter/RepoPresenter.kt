package com.example.someapp.presenter

import moxy.MvpPresenter

class RepoPresenter : MvpPresenter<RepoView>() {
    fun init(githubRepo: GithubRepo){
        viewState.init(githubRepo)
    }
}