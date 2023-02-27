package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.CategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category

class CategoryRepositoryImpl(
    private var categoryDataSource: CategoryDataSource
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return categoryDataSource.getCategories()
    }
}
