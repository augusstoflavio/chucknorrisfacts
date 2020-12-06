package br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel

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
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        facts.value = listOf()
        loading.value = false
    }

    fun searchFacts(query: String) {
        if (query.length <= 3) {
            error.value = "Preencha mais de 3 caracteres para realizar a busca"
            return
        }

        loading.value = true
        val disposable = factRepository
            .search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                loading.value = false
            }
            .subscribe (
                {
                    facts.value = it
                },
                {
                    error.value = it.message
                }
            )

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }
}