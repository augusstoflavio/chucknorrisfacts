package br.com.augusto.chucknorrisfacts.ui.extensions

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.domain.Result

fun Fragment.shareText(text: String, title: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, text)
    startActivity(Intent.createChooser(shareIntent, title))
}

fun <T> Fragment.setNavigationResult(key: String, value: T) {
    with(findNavController()) {
        previousBackStackEntry?.savedStateHandle?.set(key, value)
        navigateUp()
    }
}

fun <T> Fragment.getNavigationResult(key: String): MutableLiveData<T>? {
    return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData(
        key,
    )
}

fun Fragment.navigateUp() {
    findNavController().navigateUp()
}

fun Fragment.showAlertDialog(
    title: String,
    message: String,
    positiveButton: AlertDialogButton? = null,
    negativeButton: AlertDialogButton? = null,
) {
    val alertDialog = AlertDialog.Builder(requireContext())
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

fun Fragment.showError(error: Result.Error, tryAgainAction: () -> Unit) {
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

private fun Fragment.showDialogError(title: String, message: String, tryAgainAction: () -> Unit) {
    showAlertDialog(
        title = title,
        message = message,
        positiveButton = AlertDialogButton(
            name = requireContext().getString(R.string.try_again),
            action = {
                tryAgainAction.invoke()
                it.dismiss()
            },
        ),
        negativeButton = AlertDialogButton(
            name = requireContext().getString(R.string.ok),
            action = {
                it.dismiss()
            },
        ),
    )
}

data class AlertDialogButton(val name: String, val action: (DialogInterface) -> Unit)
