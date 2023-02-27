package br.com.augusto.chucknorrisfacts.data.local.database.dao

import br.com.augusto.chucknorrisfacts.domain.model.Category

interface CategoryDao {
    fun findAll(): MutableList<Category>?
    fun save(categories: List<Category>)
}
