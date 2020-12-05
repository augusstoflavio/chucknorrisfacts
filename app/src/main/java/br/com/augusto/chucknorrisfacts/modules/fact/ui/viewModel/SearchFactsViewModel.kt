package br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category
import br.com.augusto.chucknorrisfacts.modules.fact.data.repository.IFactRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchFactsViewModel(
    private var factRepository: IFactRepository,
    private var compositeDisposable: CompositeDisposable
): ViewModel() {

    var error: MutableLiveData<String> = MutableLiveData()
    var loadingCategories: MutableLiveData<Boolean> = MutableLiveData()

    var categories: MutableLiveData<List<Category>> = MutableLiveData()
    var lastSearchs: MutableLiveData<List<String>> = MutableLiveData()

    init {
        getCategories()
        getLastSearchs()
    }

    fun getCategories() {
        loadingCategories.value = true
        val disposable = factRepository
            .categories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                loadingCategories.value = false
            }
            .subscribe (
                {
                    categories.value = it
                },
                {
                    error.value = it.message
                }
            )

        compositeDisposable.add(disposable)
    }

    fun getLastSearchs() {
        val disposable = factRepository
            .lastSearchs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe (
                {
                    lastSearchs.value = it.map {
                        it.name!!
                    }
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