package br.com.augusto.chucknorrisfacts.domain.di

import br.com.augusto.chucknorrisfacts.domain.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single<FactRepository> {
        FactRepositoryImpl(get())
    }

    single<CategoryRepository> {
        CategoryRepositoryImpl(
            localCategoryDataSource = get(),
            remoteCategoryDataSource = get(),
        )
    }

    single<SearchRepository> {
        SearchRepositoryImpl(
            searchDataSource = get(),
        )
    }
}
