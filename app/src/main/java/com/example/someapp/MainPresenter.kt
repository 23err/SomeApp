package com.example.someapp

class MainPresenter(
    private val view: MainView,
    private val model: CounterModel = CounterModel
) {
    /*
    * Обрабатывает событие нажатия на counter1
    * */
    fun counter1Click() {
        val index = 0
        val nextValue = model.next(index)
        view.showCounter1Text(nextValue.toString())
    }

    /*
    * Обрабатывает событие нажатия на counter2
    * */
    fun counter2Click() {
        val index = 1
        val nextValue = model.next(index)
        view.showCounter2Text(nextValue.toString())
    }

    /*
    * Обрабатывает событие нажатия на counter3
    * */
    fun counter3Click() {
        val index = 2
        val nextValue = model.next(index)
        view.showCounter3Text(nextValue.toString())
    }

    /** Возращает значение по [index] */
    fun getCounter(index: Int): String {
        return model.getCurrent(index).toString()
    }
}