package br.com.augusto.chucknorrisfacts.domain.dataSource

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

interface LocalCategoryDataSource {

    suspend fun getRandomCategories(amount: Int): Result<List<Category>>
    suspend fun saveCategories(categories: List<Category>)
}
