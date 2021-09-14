package com.example.someapp

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val counters = mutableListOf(0, 0, 0)

    private val btn_counter1: Button by lazy { findViewById(R.id.btn_counter1) }
    private val btn_counter2: Button by lazy { findViewById(R.id.btn_counter2) }
    private val btn_counter3: Button by lazy { findViewById(R.id.btn_counter3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btn_counter1.setOnClickListener {
            btn_counter1.text = (++counters[0]).toString()
        }

        btn_counter2.setOnClickListener {
            btn_counter2.text = (++counters[1]).toString()
        }

        btn_counter3.setOnClickListener {
            btn_counter3.text = (++counters[2]).toString()
        }

        initViews()
    }

    fun initViews(){
        btn_counter1.text = counters[0].toString()
        btn_counter2.text = counters[1].toString()
        btn_counter3.text = counters[2].toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray("counters", counters.toIntArray())
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putIntArray("counters", counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val countersArray = savedInstanceState.getIntArray("counters")
        countersArray?.toList()?.let {
            counters.clear()
            counters.addAll(it)
        }
        initViews()
    }

}