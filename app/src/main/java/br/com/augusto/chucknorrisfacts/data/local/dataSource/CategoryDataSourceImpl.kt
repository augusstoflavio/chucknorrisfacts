package br.com.augusto.chucknorrisfacts.data.local.dataSource

import br.com.augusto.chucknorrisfacts.data.local.dao.CategoryDao
import br.com.augusto.chucknorrisfacts.data.local.entity.CategoryEntity
import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category

class CategoryDataSourceImpl(
    private val factService: FactService,
    private val categoryDao: CategoryDao,
) : CategoryDataSource {

    override suspend fun getNewCategories(): Result<List<Category>> {
        return try {
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

    override suspend fun getSavedCategories(): Result<List<Category>> {
        return try {
            val categories = categoryDao.getAll()
            Result.Success(
                categories.map {
                    Category(
                        name = it.name,
                    )
                },
            )
        } catch (e: Exception) {
            Result.Error(
                e,
            )
        }
    }

    override suspend fun saveCategories(categories: List<Category>) {
        try {
            categoryDao.insert(
                categories.map {
                    CategoryEntity(
                        name = it.name,
                    )
                },
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(
                e,
            )
        }
    }
}
