package com.example.someapp.view

import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.GithubUser
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user: GithubUser) = FragmentScreen { UserFragment.newInstance(user)}
    override fun repo(githubRepo: GithubRepo) = FragmentScreen {RepoFragment.newInstance(githubRepo)}
}