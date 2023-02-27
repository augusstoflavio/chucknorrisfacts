package br.com.augusto.chucknorrisfacts.domain

sealed interface Result<out T> {

    data class Success<T>(val data: T) : Result<T>
    data class Error<T>(val data: Exception) : Result<T>
}
