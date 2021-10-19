package com.example.someapp.presenter

import com.example.someapp.model.GithubUsersRepo
import com.example.someapp.view.IScreens
import com.example.someapp.view.UserItemView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsersView>() {

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null

        override fun getCount() = users.size

        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val observable=usersRepo.getUser(itemView.pos)
            observable.subscribe {
                router.navigateTo(screens.user(it))
            }
        }
    }

    private fun loadData() {
        val observable = usersRepo.getUsers()
        observable.subscribe {
            usersListPresenter.users.addAll(it)
        }
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}