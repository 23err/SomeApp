package com.example.someapp.view

interface UserItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}