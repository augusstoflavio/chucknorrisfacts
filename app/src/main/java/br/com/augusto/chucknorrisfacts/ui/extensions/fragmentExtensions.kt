package br.com.augusto.chucknorrisfacts.ui.extensions

import android.content.Intent
import androidx.fragment.app.Fragment

fun Fragment.shareText(text: String, title: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, text)
    startActivity(Intent.createChooser(shareIntent, title))
}