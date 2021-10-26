package com.example.someapp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    private val router: Router
) : MvpPresenter<UserView>() {

    fun showLogin(githubUser: GithubUser) {
        githubUser.login?.let{
            viewState.showLogin(it)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}