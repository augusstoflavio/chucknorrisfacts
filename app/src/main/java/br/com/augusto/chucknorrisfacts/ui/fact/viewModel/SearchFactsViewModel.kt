package br.com.augusto.chucknorrisfacts.ui.fact.viewModel

import androidx.lifecycle.ViewModel
import br.com.augusto.chucknorrisfacts.domain.repository.FactRepository
import io.reactivex.disposables.CompositeDisposable

class SearchFactsViewModel(
    private var factRepository: FactRepository,
    private var compositeDisposable: CompositeDisposable
) : ViewModel() {

//    var error: MutableLiveData<String> = MutableLiveData()
//    var loadingCategories: MutableLiveData<Boolean> = MutableLiveData()
//
//    var categories: MutableLiveData<List<Category>> = MutableLiveData()
//    var lastSearchs: MutableLiveData<List<Search>> = MutableLiveData()
//
//    init {
//        getCategories()
//        getLastSearchs()
//    }
//
//    fun getCategories() {
//        loadingCategories.value = true
//        val disposable = factRepository
//            .getCategories()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doAfterTerminate {
//                loadingCategories.value = false
//            }
//            .subscribe(
//                {
//                    categories.value = it.shuffled().take(9)
//                },
//                {
//                    error.value = it.message
//                }
//            )
//
//        compositeDisposable.add(disposable)
//    }
//
//    fun getLastSearchs() {
//        val disposable = factRepository
//            .getLatestSearches(5)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    lastSearchs.value = it
//                },
//                {
//                    error.value = it.message
//                }
//            )
//
//        compositeDisposable.add(disposable)
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        compositeDisposable.clear()
//        compositeDisposable.dispose()
//    }
}
