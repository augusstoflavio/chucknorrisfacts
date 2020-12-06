package br.com.augusto.chucknorrisfacts.modules.fact.data.dao.category

import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category

interface ICategoryDao {
    fun findAll(): MutableList<Category>?
    fun save(categories: List<Category>)
}