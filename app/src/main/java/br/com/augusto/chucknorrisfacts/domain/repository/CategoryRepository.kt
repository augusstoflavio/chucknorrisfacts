package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(amount: Int): Result<List<Category>>
    suspend fun syncCategories(): Result<List<Category>>
}
