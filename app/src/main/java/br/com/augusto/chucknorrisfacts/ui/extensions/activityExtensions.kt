package br.com.augusto.chucknorrisfacts.ui.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

fun AppCompatActivity.startDialog(dialogFragment: DialogFragment) {
    val fm = supportFragmentManager
    dialogFragment.show(fm, "dialog")
}
