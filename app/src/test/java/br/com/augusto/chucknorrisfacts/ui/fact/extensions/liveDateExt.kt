package br.com.augusto.chucknorrisfacts.ui.fact.extensions

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getTestValue(): T? {
    var value: T? = null
    val latch = CountDownLatch(1)

    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            value = t
            latch.countDown()
            this@getTestValue.removeObserver(this)
        }
    }

    this.observeForever(observer)
    latch.await(2, TimeUnit.SECONDS)

    return value
}