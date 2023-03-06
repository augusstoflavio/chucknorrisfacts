package br.com.augusto.chucknorrisfacts.data.di

val dataModule = apiModule +
    dataBaseModule +
    localDataSourceModule +
    remoteDataSourceModule
