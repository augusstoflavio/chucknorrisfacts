package br.com.augusto.chucknorrisfacts.app.module

import android.content.Context
import org.koin.core.module.Module

interface ModuleInterface {

    fun getKoinModule(context: Context): Module?
}