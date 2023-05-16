package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Search
import java.time.LocalDateTime

class SearchRepositoryImpl(
    private val searchDataSource: SearchDataSource,
) : SearchRepository {

    override fun getLatestSearches(amount: Long): Result<List<Search>> {
        return Result.Success(listOf())
    }

    override suspend fun saveSearch(search: Search): Result<Unit> {
        return searchDataSource.saveSearch(
            search
        )
    }
}
