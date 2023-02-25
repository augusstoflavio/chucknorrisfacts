package br.com.augusto.chucknorrisfacts

import android.app.Application
import br.com.augusto.chucknorrisfacts.app.module.ModuleInterface
import br.com.augusto.chucknorrisfacts.modules.fact.FactModule
import br.com.augusto.chucknorrisfacts.modules.main.MainModule
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
        Realm.init(this)
    }

    private fun startKoin() {
        org.koin.core.context.startKoin {
            androidLogger()
            androidContext(this@MainApplication)

            applicationContext

            val modulesKoin = getModules().map {
                it.getKoinModule(applicationContext)
            }.filterNotNull()

            koin.loadModules(modulesKoin)
            koin.createRootScope()
        }
    }

    fun getModules(): List<ModuleInterface> {
        return listOf(
            MainModule(),
            FactModule()
        )
    }
}