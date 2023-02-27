package br.com.augusto.chucknorrisfacts

import android.app.Application
import br.com.augusto.chucknorrisfacts.di.allModules
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        Realm.init(this)
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            koin.loadModules(allModules)
            koin.createRootScope()
        }
    }
}
