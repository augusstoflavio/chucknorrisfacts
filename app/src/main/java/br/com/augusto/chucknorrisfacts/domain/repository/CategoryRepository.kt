package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
}
