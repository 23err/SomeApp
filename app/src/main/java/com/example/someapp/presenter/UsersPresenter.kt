package com.example.someapp.presenter

import com.example.someapp.model.GithubUsersRepo
import com.example.someapp.model.IGithubUserRepo
import com.example.someapp.view.IScreens
import com.example.someapp.view.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.functions.Consumer
import moxy.MvpPresenter
import java.lang.RuntimeException
import javax.inject.Inject

class UsersPresenter(
    private val uiScheduler: Scheduler,


    ) :
    MvpPresenter<UsersView>() {

    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens
    @Inject lateinit var usersRepo: IGithubUserRepo

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let {view.setLogin(it)}
            user.avatarUrl?.let{ view.loadAvatar(it)}
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val githubUser = usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(githubUser))
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({ it ->
                usersListPresenter.users.apply {
                    clear()
                    addAll(it)
                    viewState.updateList()
                }
            }, {
                throw RuntimeException("Не удалось загрузить данные из GithubApi")
            })

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}