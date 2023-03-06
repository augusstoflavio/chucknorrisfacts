package br.com.augusto.chucknorrisfacts.ui.fact.adapter

import br.com.augusto.chucknorrisfacts.domain.model.Category

interface OnClickCategoryListener {
    fun onClick(category: Category)
}
