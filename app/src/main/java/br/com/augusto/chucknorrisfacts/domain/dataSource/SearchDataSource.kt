package br.com.augusto.chucknorrisfacts.domain.dataSource

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search

interface SearchDataSource {

    suspend fun getLatestSearches(amount: Long): Result<List<Search>>
    suspend fun saveSearch(search: Search): Result<Unit>
}
