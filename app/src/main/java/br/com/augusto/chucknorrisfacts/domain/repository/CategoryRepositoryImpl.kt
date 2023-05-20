package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CategoryRepositoryImpl(
    private val categoryDataSource: CategoryDataSource,
) : CategoryRepository {
    override suspend fun getCategories(): Flow<Result<List<Category>>> {
        return flow {
            emit(categoryDataSource.getSavedCategories())
            val newCategoriesResul = categoryDataSource.getNewCategories()
            if (newCategoriesResul is Result.Success) {
                categoryDataSource.saveCategories(newCategoriesResul.data)
            }
            emit(categoryDataSource.getNewCategories())
        }
    }
}
