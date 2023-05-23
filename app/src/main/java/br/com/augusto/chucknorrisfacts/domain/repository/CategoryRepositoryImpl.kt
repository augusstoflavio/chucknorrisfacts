package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category

class CategoryRepositoryImpl(
    private val categoryDataSource: CategoryDataSource,
) : CategoryRepository {
    override suspend fun getCategories(amount: Int): Result<List<Category>> {
        return categoryDataSource.getSavedCategories(amount)
    }

    override suspend fun syncCategories(): Result<List<Category>> {
        val newCategoriesResult = categoryDataSource.getNewCategories()
        if (newCategoriesResult !is Result.Success) {
            return newCategoriesResult
        }

        categoryDataSource.saveCategories(newCategoriesResult.data)
        return newCategoriesResult
    }
}
