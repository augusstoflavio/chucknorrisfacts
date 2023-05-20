package br.com.augusto.chucknorrisfacts.data.extension

import br.com.augusto.chucknorrisfacts.data.local.entity.CategoryEntity
import br.com.augusto.chucknorrisfacts.domain.model.Category

fun CategoryEntity.toCategory(): Category {
    return Category(
        name = this.name
    )
}