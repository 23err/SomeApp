package com.example.someapp.view

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(text: String): Screen
}