package br.com.augusto.chucknorrisfacts.di

import br.com.augusto.chucknorrisfacts.data.di.dataModule
import br.com.augusto.chucknorrisfacts.domain.di.domainModules

val allModules = domainModules + dataModule
