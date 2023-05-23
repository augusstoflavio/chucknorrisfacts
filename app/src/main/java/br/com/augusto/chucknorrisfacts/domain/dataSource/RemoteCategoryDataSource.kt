package br.com.augusto.chucknorrisfacts.domain.dataSource

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

interface RemoteCategoryDataSource {

    suspend fun getCategories(): Result<List<Category>>
}