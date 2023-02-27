package br.com.augusto.chucknorrisfacts.data.di

import br.com.augusto.chucknorrisfacts.data.remote.dataSource.CategoryDataSourceImpl
import br.com.augusto.chucknorrisfacts.data.remote.dataSource.FactDataSourceImpl
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.dataSource.FactDataSource
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single<CategoryDataSource> {
        CategoryDataSourceImpl(get())
    }

    single<FactDataSource> {
        FactDataSourceImpl(get())
    }
}