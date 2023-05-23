package br.com.augusto.chucknorrisfacts.data.di

import br.com.augusto.chucknorrisfacts.data.dataSource.LocalCategoryDataSourceImpl
import br.com.augusto.chucknorrisfacts.data.dataSource.FactDataSourceImpl
import br.com.augusto.chucknorrisfacts.data.dataSource.RemoteCategoryDataSourceImpl
import br.com.augusto.chucknorrisfacts.data.dataSource.SearchDataSourceImpl
import br.com.augusto.chucknorrisfacts.domain.dataSource.LocalCategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.dataSource.FactDataSource
import br.com.augusto.chucknorrisfacts.domain.dataSource.RemoteCategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<SearchDataSource> {
        SearchDataSourceImpl(
            chuckNorrisFactsDatabase = get(),
        )
    }

    single<LocalCategoryDataSource> {
        LocalCategoryDataSourceImpl(
            categoryDao = get(),
        )
    }

    single<RemoteCategoryDataSource> {
        RemoteCategoryDataSourceImpl(
            factService = get()
        )
    }

    single<FactDataSource> {
        FactDataSourceImpl(
            factService = get()
        )
    }
}
