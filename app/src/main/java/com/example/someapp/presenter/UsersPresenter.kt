package com.example.someapp.presenter

import com.example.someapp.model.GithubUsersRepo
import com.example.someapp.view.IScreens
import com.example.someapp.view.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.functions.Consumer
import moxy.MvpPresenter
import java.lang.RuntimeException

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
            user.login?.let{
                view.setLogin(it)
            }
        }
    }

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
//            val observable = usersRepo.getUser(itemView.pos)
//            observable.subscribe {
//                router.navigateTo(screens.user(it))
//            }
        }
    }

    private fun loadData() {
        usersRepo.getUsers()
            .subscribe ({ it ->
                usersListPresenter.users.apply {
                    clear()
                    addAll(it)
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