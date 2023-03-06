package br.com.augusto.chucknorrisfacts.domain

sealed interface Result<out T> {

    data class Success<out T>(val data: T) : Result<T>
    data class Error(val data: Exception) : Result<Nothing>
}
