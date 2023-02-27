package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Search

class SearchRepositoryImpl(
    private var searchDataSource: SearchDataSource
) : SearchRepository {

    override fun getLatestSearches(amount: Long): Result<List<Search>> {
        return searchDataSource.getLatestSearches(amount)
    }
}
