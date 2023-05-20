package br.com.augusto.chucknorrisfacts.data.extension

import br.com.augusto.chucknorrisfacts.data.local.entity.CategoryEntity
import br.com.augusto.chucknorrisfacts.domain.model.Category

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        name = this.name,
    )
}
