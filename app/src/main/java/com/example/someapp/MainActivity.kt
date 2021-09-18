package com.example.someapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.someapp.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import java.lang.RuntimeException

class MainActivity : MvpAppCompatActivity(R.layout.activity_main), MainView {

    val presenter = MainPresenter()
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setClickListeners()
    }

    /* Показывает [text] в кнопке counter1*/
    override fun showCounterOneText(text: String) = with(binding) {
        btnCounter1.text = text
    }


    /* Показывает [text] в кнопке counter2*/
    override fun showCounterTwoText(text: String) = with(binding) {
        btnCounter2.text = text
    }

    /* Показывает [text] в кнопке counter3*/
    override fun showCounterThreeText(text: String) = with(binding) {
        btnCounter3.text = text
    }

    override fun showNotification(text: String) {
        Toast.makeText(baseContext, text, Toast.LENGTH_SHORT).show()
    }

    /** Устанавливает listener для кнопок*/
    private fun setClickListeners() = with(binding) {
        btnCounter1.setOnClickListener { presenter.counter1Click() }
        btnCounter2.setOnClickListener { presenter.counter2Click() }
        btnCounter3.setOnClickListener { presenter.counter3Click() }
    }
}