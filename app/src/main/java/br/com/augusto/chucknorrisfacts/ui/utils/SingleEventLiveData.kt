package br.com.augusto.chucknorrisfacts.ui.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleEventLiveData<T> : MutableLiveData<T>() {

    private val isNotified = AtomicBoolean(false)

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner) {
            if (isNotified.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }
    }

    override fun setValue(value: T?) {
        isNotified.set(true)
        super.setValue(value)
    }

    override fun postValue(value: T?) {
        isNotified.set(true)
        super.postValue(value)
    }
}
