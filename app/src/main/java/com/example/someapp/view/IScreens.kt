package com.example.someapp.view

import com.example.someapp.presenter.GithubRepo
import com.example.someapp.presenter.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
    fun repo(githubRepo: GithubRepo): Screen
}