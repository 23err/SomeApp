package com.example.someapp.presenter

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val login:String
): Parcelable
