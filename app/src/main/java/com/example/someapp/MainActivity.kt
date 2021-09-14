package com.example.someapp

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main), MainView {

    val presenter = MainPresenter(this)

    private val btn_counter1: Button by lazy { findViewById(R.id.btn_counter1) }
    private val btn_counter2: Button by lazy { findViewById(R.id.btn_counter2) }
    private val btn_counter3: Button by lazy { findViewById(R.id.btn_counter3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        btn_counter1.setOnClickListener(listener)
        btn_counter2.setOnClickListener(listener)
        btn_counter3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when(index){
            0->btn_counter1.text = text
            1->btn_counter2.text = text
            2->btn_counter3.text = text
        }
    }
}