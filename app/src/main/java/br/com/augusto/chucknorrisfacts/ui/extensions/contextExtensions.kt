package br.com.augusto.chucknorrisfacts.ui.extensions

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.Result

fun Context.showAlertDialog(
    title: String,
    message: String,
    positiveButton: AlertDialogButton? = null,
    negativeButton: AlertDialogButton? = null,
) {
    val alertDialog = AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)

    positiveButton?.let {
        alertDialog.setPositiveButton(it.name) { dialog, _ ->
            it.action(dialog)
        }
    }

    negativeButton?.let {
        alertDialog.setNegativeButton(it.name) { dialog, _ ->
            it.action(dialog)
        }
    }

    alertDialog.show()
}

fun Context.showError(error: Result.Error, tryAgainAction: () -> Unit) {
    when (error) {
        Result.Error.ApiError -> {
            showDialogError(
                title = getString(R.string.api_error_title),
                message = getString(R.string.api_error_message),
                tryAgainAction = tryAgainAction,
            )
        }
        is Result.Error.InternalError -> {
            showDialogError(
                title = getString(R.string.internal_error_title),
                message = getString(R.string.internal_error_message),
                tryAgainAction = tryAgainAction,
            )
        }
        Result.Error.NoConnectionError -> {
            showDialogError(
                title = getString(R.string.no_connection_error_title),
                message = getString(R.string.no_connection_error_message),
                tryAgainAction = tryAgainAction,
            )
        }
    }
}

private fun Context.showDialogError(title: String, message: String, tryAgainAction: () -> Unit) {
    showAlertDialog(
        title = title,
        message = message,
        positiveButton = AlertDialogButton(
            name = getString(R.string.try_again),
            action = {
                tryAgainAction.invoke()
                it.dismiss()
            },
        ),
        negativeButton = AlertDialogButton(
            name = getString(R.string.ok),
            action = {
                it.dismiss()
            },
        ),
    )
}

data class AlertDialogButton(val name: String, val action: (DialogInterface) -> Unit)