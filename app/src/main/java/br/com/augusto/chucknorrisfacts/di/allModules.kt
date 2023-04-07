package br.com.augusto.chucknorrisfacts.di

import br.com.augusto.chucknorrisfacts.data.di.dataModules
import br.com.augusto.chucknorrisfacts.domain.di.domainModules
import br.com.augusto.chucknorrisfacts.ui.di.uiModule

val allModules = domainModules + dataModules + uiModule
