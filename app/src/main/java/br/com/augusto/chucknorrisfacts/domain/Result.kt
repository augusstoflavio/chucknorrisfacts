package br.com.augusto.chucknorrisfacts.domain

sealed interface Result<out T> {

    data class Success<out T>(val data: T) : Result<T>
    sealed interface Error : Result<Nothing> {

        object ApiError : Error

        object NoConnectionError : Error

        data class InternalError(val exception: Exception) : Error
    }
}
