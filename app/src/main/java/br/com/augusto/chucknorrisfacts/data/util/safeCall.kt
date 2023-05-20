package br.com.augusto.chucknorrisfacts.data.util

import br.com.augusto.chucknorrisfacts.domain.Result

suspend fun <T : Any> safeCall(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (e: Exception) {
        Result.Error(e)
    }
}