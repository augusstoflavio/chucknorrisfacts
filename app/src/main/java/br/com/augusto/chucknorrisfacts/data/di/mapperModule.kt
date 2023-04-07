package br.com.augusto.chucknorrisfacts.data.di

import br.com.augusto.chucknorrisfacts.data.remote.dataSource.mapper.FactMapper
import br.com.augusto.chucknorrisfacts.data.remote.dataSource.mapper.FactMapperImpl
import org.koin.dsl.module

val mapperModule = module {
    factory<FactMapper> {
        FactMapperImpl()
    }
}
