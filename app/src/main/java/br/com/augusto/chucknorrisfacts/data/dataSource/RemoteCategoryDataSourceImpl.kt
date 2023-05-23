package br.com.augusto.chucknorrisfacts.data.dataSource

import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.data.util.safeCall
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.RemoteCategoryDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Category

class RemoteCategoryDataSourceImpl(
    private val factService: FactService,
) : RemoteCategoryDataSource {

    override suspend fun getCategories(): Result<List<Category>> {
        return safeCall {
            val response = factService.categories()
            response.body()?.map {
                Category(
                    name = it,
                )
            } ?: listOf()
        }
    }
}