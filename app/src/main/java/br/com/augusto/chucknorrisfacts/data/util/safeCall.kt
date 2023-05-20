package br.com.augusto.chucknorrisfacts.data.util

import br.com.augusto.chucknorrisfacts.data.remote.exception.ApiException
import br.com.augusto.chucknorrisfacts.data.remote.exception.NoConnectivityException
import br.com.augusto.chucknorrisfacts.domain.Result

suspend fun <T : Any> safeCall(
    call: suspend () -> T
): Result<T> {
    return try {
        Result.Success(call.invoke())
    } catch (e: NoConnectivityException) {
        Result.Error.NoConnectionError
    } catch (e: ApiException) {
        Result.Error.ApiError
    } catch (e: Exception) {
        Result.Error.InternalError(e)
    }
}