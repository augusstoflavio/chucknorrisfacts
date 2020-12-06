package br.com.augusto.chucknorrisfacts.modules.main

import android.content.Context
import br.com.augusto.chucknorrisfacts.app.api.RetrofitFactory
import br.com.augusto.chucknorrisfacts.app.commons.NetworkState
import br.com.augusto.chucknorrisfacts.app.database.Database
import br.com.augusto.chucknorrisfacts.app.module.ModuleInterface
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

class MainModule: ModuleInterface {

    override fun getKoinModule(context: Context): Module? {
        return module {
            factory {
                RetrofitFactory.getInstance()
            }

            factory {
                Database.getInstance()
            }

            single {
                NetworkState(androidContext())
            }
        }
    }
}