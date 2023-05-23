package br.com.augusto.chucknorrisfacts.ui.di

import br.com.augusto.chucknorrisfacts.ui.fact.viewModel.FactsViewModel
import br.com.augusto.chucknorrisfacts.ui.fact.viewModel.SearchFactsViewModel
import br.com.augusto.chucknorrisfacts.ui.main.viewModel.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FactsViewModel(
            searchFactsUseCase = get(),
            factUiMapper = get(),
        )
    }

    viewModel {
        SearchFactsViewModel(
            getLatestSearchesUseCase = get(),
            getCategoriesUseCase = get(),
        )
    }

    viewModel {
        MainViewModel(
            syncCategoriesUseCase = get(),
        )
    }
}
