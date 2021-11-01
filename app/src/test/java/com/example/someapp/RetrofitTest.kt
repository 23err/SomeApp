package com.example.someapp

import com.example.someapp.model.RetrofitGithub
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test

class RetrofitTest {
    @Test
    fun retrofit_test(){

        val githubClient = RetrofitGithub.api
        githubClient.loadUsers().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe({
                       println(it.toString())
            },{
                println("error: ${it.message}")
            })
        Thread.sleep(3000)
    }
}