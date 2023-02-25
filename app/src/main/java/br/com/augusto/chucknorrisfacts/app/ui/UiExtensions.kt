package br.com.augusto.chucknorrisfacts.app.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

fun AppCompatActivity.startDialog(dialogFragment: DialogFragment) {
    val fm = supportFragmentManager
    val yourDialog = dialogFragment
    yourDialog.show(fm, "dialog")
}
