package com.example.someapp.view

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}