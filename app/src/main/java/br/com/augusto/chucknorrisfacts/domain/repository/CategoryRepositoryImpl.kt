package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(
    private val factService: FactService,
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = factService.categories()
                Result.Success(
                    response.body()?.map {
                        Category(
                            name = it,
                        )
                    } ?: listOf(),
                )
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}
