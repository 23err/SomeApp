package com.example.someapp

object CounterModel {
    val counters = mutableListOf(0, 0, 0)

    /** Возвращает значение counters по [index] */
    fun getCurrent(index: Int) = counters[index]

    /** Увеличивает счетчик counters по [index] и возвращает результат*/
    fun next(index: Int) = ++counters[index]

    /** Устанавливает [value] counters по [index] */
    fun set(index: Int, value: Int) {
        counters[index] = value
    }
}