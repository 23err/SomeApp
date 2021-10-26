package com.example.someapp

import okhttp3.*
import org.junit.Test
import java.io.IOException

class OkHttpTest {
    @Test
    fun okHttp_test(){
        val client = OkHttpClient.Builder().build()
        val request = Request.Builder()
            .url("https://api.github.com/users/googlesamples")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("error: e.message")
            }

            override fun onResponse(call: Call, response: Response) {
                println("response: ${response.body?.string()}")
            }

        })

        Thread.sleep(3000)
    }
}