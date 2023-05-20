package br.com.augusto.chucknorrisfacts.data.di

import br.com.augusto.chucknorrisfacts.data.dataSource.CategoryDataSourceImpl
import br.com.augusto.chucknorrisfacts.data.dataSource.SearchDataSourceImpl
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<SearchDataSource> {
        SearchDataSourceImpl(
            chuckNorrisFactsDatabase = get(),
        )
    }

    single<CategoryDataSource> {
        CategoryDataSourceImpl(
            factService = get(),
            categoryDao = get(),
        )
    }
}
