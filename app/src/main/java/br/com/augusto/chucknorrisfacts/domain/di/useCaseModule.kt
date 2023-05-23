package br.com.augusto.chucknorrisfacts.domain.di

import br.com.augusto.chucknorrisfacts.domain.useCase.GetCategoriesUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.GetLatestSearchesUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.SearchFactsUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.SyncCategoriesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetCategoriesUseCase(get())
    }

    factory {
        GetLatestSearchesUseCase(get())
    }

    factory {
        SearchFactsUseCase(get(), get())
    }

    factory {
        SyncCategoriesUseCase(get())
    }
}
