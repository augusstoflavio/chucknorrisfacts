package br.com.augusto.chucknorrisfacts.data.di

import br.com.augusto.chucknorrisfacts.data.local.database.Database
import org.koin.dsl.module

val dataBaseModule = module {
    factory {
        Database.getInstance()
    }
}