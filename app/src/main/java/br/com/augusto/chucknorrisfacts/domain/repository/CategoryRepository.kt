package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getCategories(): Flow<Result<List<Category>>>
}
