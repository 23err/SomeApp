package com.example.someapp.presenter

import com.example.someapp.view.IScreens
import com.example.someapp.view.MainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}