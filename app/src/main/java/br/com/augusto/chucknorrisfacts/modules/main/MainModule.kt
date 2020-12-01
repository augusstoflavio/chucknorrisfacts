package br.com.augusto.chucknorrisfacts.modules.main

import android.content.Context
import br.com.augusto.chucknorrisfacts.app.module.ModuleInterface
import org.koin.core.module.Module

class MainModule: ModuleInterface {

    override fun getKoinModule(context: Context): Module? {
        return null
    }
}