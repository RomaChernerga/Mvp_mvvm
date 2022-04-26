package com.example.mvp_mvvm.utils

import android.os.Handler

private class Subscriber<T>(
    private val handler: Handler,
    private val callback: (T?) -> Unit
) {
    fun invoke(value: T?) {
        handler.post{
           callback.invoke(value)
        }
    }
}


// 1) Toast показывается каждый раз
// 2) Поддержка многопоточности
// 3) unSubscribe

class Publisher<T>(private val isSingle: Boolean = false) {

    private val subscribers: MutableSet<Subscriber<T?>> = mutableSetOf()
    //переменная для запоминания последнего значения для использования при подписках
    var value: T? = null
        private set
    private var hasFirstValue = false

    //чтобы подписаться
    fun subscribe(handler: Handler, callback: (T?) -> Unit) {
        val subscriber = Subscriber(handler, callback)
        subscribers.add(subscriber)
        if (hasFirstValue) {
            subscriber.invoke(value)

        }
    }


    //чтобы отписать всех
    fun unSubscribeAll(){
        subscribers.clear()
    }

    fun post(value: T) {
        if(!isSingle) {
            hasFirstValue = true
            this.value = value
        }
        subscribers.forEach {it.invoke(value)}
    }
}