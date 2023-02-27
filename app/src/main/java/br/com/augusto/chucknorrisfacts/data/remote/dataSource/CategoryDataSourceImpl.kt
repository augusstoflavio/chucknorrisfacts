package br.com.augusto.chucknorrisfacts.data.remote.dataSource

import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CategoryDataSourceImpl(
    private val factService: FactService
) : CategoryDataSource {
    override suspend fun getCategories(): Result<List<Category>> {
        return withContext(Dispatchers.IO) {
            try {
                factService.categories()
                Result.Success(
                    listOf()
                )
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}
