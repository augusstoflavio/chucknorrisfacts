package br.com.augusto.chucknorrisfacts.ui.di

import br.com.augusto.chucknorrisfacts.ui.fact.mapper.FactUiMapper
import br.com.augusto.chucknorrisfacts.ui.fact.mapper.FactUiMapperImpl
import org.koin.dsl.module

val mapperModule = module {
    factory<FactUiMapper> {
        FactUiMapperImpl()
    }
}
