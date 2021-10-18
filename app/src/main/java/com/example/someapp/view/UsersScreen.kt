package com.example.someapp.view

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class UsersScreen : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}