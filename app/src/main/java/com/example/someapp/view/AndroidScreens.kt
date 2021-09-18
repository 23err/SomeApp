package com.example.someapp.view

import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}