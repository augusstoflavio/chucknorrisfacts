package br.com.augusto.chucknorrisfacts.modules.fact.ui.dialog

import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.app.ui.FullScreenDialog

class FilterFactsDialog: FullScreenDialog() {

    override fun getDialogTitle(): String {
        return "Search facts"
    }

    override fun getContent(): Int {
        return R.layout.dialog_filter_facts
    }
}