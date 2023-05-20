package br.com.augusto.chucknorrisfacts.data.dataSource

import br.com.augusto.chucknorrisfacts.data.extension.toCategory
import br.com.augusto.chucknorrisfacts.data.extension.toCategoryEntity
import br.com.augusto.chucknorrisfacts.data.local.dao.CategoryDao
import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.data.util.safeCall
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category

class CategoryDataSourceImpl(
    private val factService: FactService,
    private val categoryDao: CategoryDao,
) : CategoryDataSource {

    override suspend fun getNewCategories(): Result<List<Category>> {
        return safeCall {
            val response = factService.categories()
            response.body()?.map {
                Category(
                    name = it,
                )
            } ?: listOf()
        }
    }

    override suspend fun getSavedCategories(): Result<List<Category>> {
        return safeCall {
            val categories = categoryDao.getAll()
            categories.map {
                it.toCategory()
            }
        }
    }

    override suspend fun saveCategories(categories: List<Category>) {
        safeCall {
            categoryDao.insert(
                categories.map {
                    it.toCategoryEntity()
                },
            )
        }
    }
}
