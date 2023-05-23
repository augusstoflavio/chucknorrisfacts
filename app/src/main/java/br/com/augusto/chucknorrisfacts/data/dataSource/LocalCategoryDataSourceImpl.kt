package br.com.augusto.chucknorrisfacts.data.dataSource

import br.com.augusto.chucknorrisfacts.data.extension.toCategory
import br.com.augusto.chucknorrisfacts.data.extension.toCategoryEntity
import br.com.augusto.chucknorrisfacts.data.local.dao.CategoryDao
import br.com.augusto.chucknorrisfacts.data.util.safeCall
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.LocalCategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category

class LocalCategoryDataSourceImpl(
    private val categoryDao: CategoryDao,
) : LocalCategoryDataSource {

    override suspend fun getRandomCategories(amount: Int): Result<List<Category>> {
        return safeCall {
            val categories = categoryDao.getAll(amount)
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
