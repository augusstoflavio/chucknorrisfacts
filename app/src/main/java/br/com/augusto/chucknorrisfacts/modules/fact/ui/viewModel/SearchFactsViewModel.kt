package br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.domain.model.Search
import br.com.augusto.chucknorrisfacts.domain.repository.FactRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchFactsViewModel(
    private var factRepository: FactRepository,
    private var compositeDisposable: CompositeDisposable
) : ViewModel() {

    var error: MutableLiveData<String> = MutableLiveData()
    var loadingCategories: MutableLiveData<Boolean> = MutableLiveData()

    var categories: MutableLiveData<List<Category>> = MutableLiveData()
    var lastSearchs: MutableLiveData<List<Search>> = MutableLiveData()

    init {
        getCategories()
        getLastSearchs()
    }

    fun getCategories() {
        loadingCategories.value = true
        val disposable = factRepository
            .getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doAfterTerminate {
                loadingCategories.value = false
            }
            .subscribe(
                {
                    categories.value = it.shuffled().take(9)
                },
                {
                    error.value = it.message
                }
            )

        compositeDisposable.add(disposable)
    }

    fun getLastSearchs() {
        val disposable = factRepository
            .getLatestSearches(5)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    lastSearchs.value = it
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
