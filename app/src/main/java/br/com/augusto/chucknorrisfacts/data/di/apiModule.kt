package br.com.augusto.chucknorrisfacts.data.di

import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.data.remote.RetrofitFactory
import org.koin.dsl.module

val apiModule = module {
    factory {
        RetrofitFactory.getInstance().create(
            FactService::class.java,
        )
    }
}
