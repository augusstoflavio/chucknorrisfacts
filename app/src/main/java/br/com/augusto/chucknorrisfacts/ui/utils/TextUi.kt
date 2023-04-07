package br.com.augusto.chucknorrisfacts.ui.utils

import android.content.Context
import androidx.annotation.StringRes

sealed class TextUi {

    data class StringResource(@StringRes val resId: Int) : TextUi()
    data class DirectString(val string: String) : TextUi()

    fun toString(context: Context): String {
        return when (this) {
            is DirectString -> this.string
            is StringResource -> context.getString(this.resId)
        }
    }
}
