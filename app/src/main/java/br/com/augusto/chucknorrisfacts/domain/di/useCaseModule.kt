package br.com.augusto.chucknorrisfacts.domain.di

import br.com.augusto.chucknorrisfacts.domain.useCase.*
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetRandomCategoriesUseCase> {
        GetRandomCategoriesUseCaseImpl(get())
    }

    factory<GetLatestSearchesUseCase> {
        GetLatestSearchesUseCaseImpl(get())
    }

    factory<SearchFactsUseCase> {
        SearchFactsUseCaseImpl(get(), get())
    }

    factory<SyncCategoriesUseCase> {
        SyncCategoriesUseCaseImpl(get())
    }
}
