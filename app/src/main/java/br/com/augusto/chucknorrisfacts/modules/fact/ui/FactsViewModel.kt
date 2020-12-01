package br.com.augusto.chucknorrisfacts.modules.fact.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import br.com.augusto.chucknorrisfacts.modules.fact.data.repository.IFactRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FactsViewModel(
    private var factRepository: IFactRepository,
    private var compositeDisposable: CompositeDisposable
): ViewModel() {

    var facts: MutableLiveData<List<Fact>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    init {
        facts.value = listOf()

    }

    fun searchFacts(query: String) {
        val disposable = factRepository
            .search(query)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnError {
                error.value = it.message
            }
            .subscribe {
                facts.value = it
            }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}