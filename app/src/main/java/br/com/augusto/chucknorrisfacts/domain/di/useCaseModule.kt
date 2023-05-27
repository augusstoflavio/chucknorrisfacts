package br.com.augusto.chucknorrisfacts.domain.di

import br.com.augusto.chucknorrisfacts.domain.useCase.*
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

    factory<SyncCategoriesUseCase> {
        SyncCategoriesUseCaseImpl(get())
    }
}
