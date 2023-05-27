package br.com.augusto.chucknorrisfacts.domain.di

import br.com.augusto.chucknorrisfacts.domain.useCase.GetRandomCategoriesUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.GetLatestSearchesUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.SearchFactsUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.SyncCategoriesUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetRandomCategoriesUseCase(get())
    }

    factory {
        GetLatestSearchesUseCase(get())
    }

    factory {
        SearchFactsUseCase(get(), get())
    }

    factory {
        SyncCategoriesUseCaseImpl(get())
    }
}
