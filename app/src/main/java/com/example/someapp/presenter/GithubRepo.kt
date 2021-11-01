package com.example.someapp.presenter

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepo(
    @Expose val id : Int? = null,
    @Expose val name : String? = null,
    @Expose val htmlUrl : String? = null,
    @Expose val url : String? = null,
    @Expose val forks : Int? = null,

): Parcelable