package com.example.someapp

import moxy.MvpPresenter

class MainPresenter(
    private val view: MainView,
    private val model: CounterModel = CounterModel
) : MvpPresenter<MainView>(){
    /*
    * Обрабатывает событие нажатия на counter1
    * */
    fun counter1Click() {
        val index = 0
        val nextValue = model.next(index)
        view.showCounterOneText(nextValue.toString())
    }

    /*
    * Обрабатывает событие нажатия на counter2
    * */
    fun counter2Click() {
        val index = 1
        val nextValue = model.next(index)
        view.showCounterTwoText(nextValue.toString())
    }

    /*
    * Обрабатывает событие нажатия на counter3
    * */
    fun counter3Click() {
        val index = 2
        val nextValue = model.next(index)
        view.showCounterThreeText(nextValue.toString())
    }

    /** Возращает значение по [index] */
    fun getCounter(index: Int): String {
        return model.getCurrent(index).toString()
    }
}