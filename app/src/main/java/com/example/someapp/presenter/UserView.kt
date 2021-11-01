package com.example.someapp.presenter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun showLogin(text: String)
    fun loadAvatar(url: String)
    fun initRepos()
    fun updateReposList()
}