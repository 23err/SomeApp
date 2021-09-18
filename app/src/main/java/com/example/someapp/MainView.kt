package com.example.someapp

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView{
    fun showCounterOneText(text: String)
    fun showCounterTwoText(text: String)
    fun showCounterThreeText(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showNotification(text: String)
}
