package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Search

class SearchRepositoryImpl(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {

    override suspend fun getLatestSearches(amount: Int): Result<List<Search>> {
        return searchDataSource.getLatestSearches(amount)
    }

    override suspend fun saveSearch(search: Search): Result<Unit> {
        return searchDataSource.saveSearch(
            search,
        )
    }
}
