package br.com.augusto.chucknorrisfacts.data.di

import androidx.room.Room
import br.com.augusto.chucknorrisfacts.data.local.database.ChuckNorrisFactsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            this.androidContext(),
            ChuckNorrisFactsDatabase::class.java,
            "chuck-norris-facts",
        ).build()
    }

    single {
        get<ChuckNorrisFactsDatabase>().getSearchDao()
    }

    single {
        get<ChuckNorrisFactsDatabase>().getCategoryDao()
    }
}
