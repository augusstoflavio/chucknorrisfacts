package br.com.augusto.chucknorrisfacts.data.di

import br.com.augusto.chucknorrisfacts.data.local.dataSource.SearchDataSourceImpl
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<SearchDataSource> {
        SearchDataSourceImpl(
            chuckNorrisFactsDatabase = get(),
        )
    }
}
