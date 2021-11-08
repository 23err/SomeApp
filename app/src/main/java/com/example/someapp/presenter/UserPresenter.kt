package com.example.someapp.presenter

import com.example.someapp.model.GithubUsersRepo
import com.example.someapp.model.IGithubUserRepo
import com.example.someapp.model.IRepositoriesRepo
import com.example.someapp.view.IRepoItemView
import com.example.someapp.view.IRepoListPresenter
import com.example.someapp.view.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject

class UserPresenter : MvpPresenter<UserView>() {
    @Inject lateinit var router: Router
    @Inject lateinit var reposRepo: IRepositoriesRepo
    @Inject lateinit var uiScheduler: Scheduler
    @Inject lateinit var screens: IScreens

    class ReposListPresenter : IRepoListPresenter {
        var reposList = mutableListOf<GithubRepo>()
        override var itemClickListener: ((IRepoItemView) -> Unit)? = null
        override fun bindView(view: IRepoItemView) {
            val repo = reposList[view.pos]
            repo.name?.let { view.setName(it) }
        }

        override fun getCount() = reposList.size
    }

    val reposListPresenter = ReposListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRepos()
        reposListPresenter.itemClickListener = {
            val repo = reposListPresenter.reposList[it.pos]
            router.navigateTo(screens.repo(repo))
        }
    }

    private fun loadRepos(githubUser: GithubUser) {
        reposRepo.getRepositories(githubUser)
            .observeOn(uiScheduler)
            .subscribe { list ->
                reposListPresenter.reposList.apply {
                    clear()
                    addAll(list)
                    viewState.updateReposList()
                }
            }
    }

    fun init(githubUser: GithubUser) {
        githubUser.login?.let {
            viewState.showLogin(it)
        }
        loadRepos(githubUser)
        githubUser.avatarUrl?.let { viewState.loadAvatar(it) }

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}