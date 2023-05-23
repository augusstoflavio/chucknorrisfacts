package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.LocalCategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.dataSource.RemoteCategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category

class CategoryRepositoryImpl(
    private val localCategoryDataSource: LocalCategoryDataSource,
    private val remoteCategoryDataSource: RemoteCategoryDataSource
) : CategoryRepository {
    override suspend fun getRandomCategories(amount: Int): Result<List<Category>> {
        return localCategoryDataSource.getRandomCategories(amount)
    }

    override suspend fun syncCategories(): Result<List<Category>> {
        val newCategoriesResult = remoteCategoryDataSource.getCategories()
        if (newCategoriesResult !is Result.Success) {
            return newCategoriesResult
        }

        localCategoryDataSource.saveCategories(newCategoriesResult.data)
        return newCategoriesResult
    }
}
