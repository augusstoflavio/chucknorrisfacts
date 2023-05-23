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
