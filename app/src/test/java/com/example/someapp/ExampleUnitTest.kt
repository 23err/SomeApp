package com.example.someapp

import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun flatMap_test() {
        val arr = listOf(1, 2, 3, 4, 5)
        Observable.fromIterable(arr)
            .flatMap {
                val timeDelay = Random.nextLong(1, 5)
                println("timedelay = $timeDelay")
                return@flatMap Observable.just("switch $it").delay(timeDelay, TimeUnit.SECONDS)
            }
            .subscribe {
                println("switch = $it")
            }
        Thread.sleep(5000)
    }

    @Test
    fun switchMap_test() {
        val arr = listOf(1, 2, 3, 4, 5)
        Observable.fromIterable(arr)
            .switchMap {
                val timeDelay = Random.nextLong(1, 5)
                println("timedelay = $timeDelay")
                return@switchMap Observable.just("switch $it").delay(timeDelay, TimeUnit.SECONDS)
            }
            .subscribe {
                println("switch = $it")
            }
        Thread.sleep(5000)
    }

    @Test
    fun concat_test() {
        val observable1 = Observable.create<Int> {
            it.onComplete()
        }
        val observable2 = Observable.just(0)
        val observable3 = Observable.just(7, 8, 9)

        Observable.concat(observable1, observable2, observable3)
            .filter { return@filter it != 0 }
//            .first(0)
            .flatMap({
                return@flatMap Observable.just(2)
            }, { it1, it2 ->
                println("return $it1 - $it2")
                return@flatMap listOf(it1, it2)
            })
            .subscribe {
                println(" concat $it")
            }
        Thread.sleep(5000)

    }

    @Test
    fun zip_test() {
        fun getUserDetails() = Observable.just("user details", " user detail 2")
        fun getUserPhoto() = Observable.just("user photo", "user photo 2")
        fun getUserName() = Observable.just("phil")

        fun getUserList() =
            Observable.zip(getUserDetails(), getUserPhoto(), getUserName(), { it1, it2, it3 ->
                return@zip listOf(it1, it2, it3)
            })

        Observable.just(1, 2, 3)
            .flatMap { return@flatMap getUserList() }
            .subscribe {
                println(it.toString())
            }

        Thread.sleep(5000)
    }


    @Test
    fun switchMapDebounce_test() {
        fun searchUser(query: String) =
            Observable.just("fil$query", "max$query", "date$query")


        val subject = PublishSubject.create<String>().apply {
            Observable.just("whu").subscribe {
                onNext("why??")
            }
        }
        subject
            .debounce(1, TimeUnit.SECONDS)
            .flatMap { return@flatMap Observable.just("asf $it") }
            .subscribe {
                println("subject $it")
            }

        subject.onNext("hellodddd")
        subject.onNext("one tow")
        Thread.sleep(5000)
    }

    @Test
    fun throwableObservable() {
        Observable.create<String> {
            val random = Random.nextInt(1, 10)
            println("new item")
            if (random % 2 == 0)
                it.onNext("hello")
            else
                it.onError(RuntimeException("Не пришло новое значение"))
            it.onComplete()
        }
            .retryWhen {
                return@retryWhen it.take(3).delay(1, TimeUnit.SECONDS)
            }
            .subscribe({
                println("success: $it")
            }, {
                println("error: ${it.message}")
            }, {
                println("complete")
            })
        Thread.sleep(5000)
    }

    @Test
    fun takeObservable_test() {
        Observable.create<String> {
            val random = Random.nextInt(1, 10)
            println("new item")
            if (random % 2 == 0)
                it.onNext("hello")
            else
                it.onError(RuntimeException("Не пришло новое значение"))
            it.onComplete()
        }
            .take(5)
            .subscribe({
                println("success: $it")
            }, {
                println("error: ${it.message}")
            }, {
                println("complete")
            })
        Thread.sleep(5000)
    }

    @Test
    fun repeatWhen_test() {
        fun getUsers() = Observable.create<String>{
            it.onNext("asdhlkjh")
            it.onComplete()
        }

        getUsers()
            .repeatWhen{
                return@repeatWhen it.delay(1, TimeUnit.SECONDS).take(2)
            }
            .subscribe {
                println("success: $it")
            }
        Thread.sleep(5000)
    }

    @Test
    fun takeUntil_test() {
        fun getUsers() = Observable.create<String>{
            val random = Random.nextInt(1,10)
            if (random %2 ==0){
                it.onNext("asdhlkjh")
            } else {
                it.onNext("hello")
            }
            it.onComplete()
        }

        getUsers()
            .repeatWhen{
                return@repeatWhen it.delay(1, TimeUnit.SECONDS)
            }
            .takeUntil{
                return@takeUntil it != "hello"
            }
            .subscribe {
                println("success: $it")
            }
        Thread.sleep(5000)
    }

}