package br.com.augusto.chucknorrisfacts.data.local.dataSource

import br.com.augusto.chucknorrisfacts.data.local.database.ChuckNorrisFactsDatabase
import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Search
import java.time.LocalDateTime

class SearchDataSourceImpl(
    private val chuckNorrisFactsDatabase: ChuckNorrisFactsDatabase,
) : SearchDataSource {
    override suspend fun getLatestSearches(amount: Long): Result<List<Search>> {
        return Result.Success(listOf())
    }

    override suspend fun saveSearch(search: Search): Result<Unit> {
        return try {
            chuckNorrisFactsDatabase.getSearchDao().insert(
                SearchEntity(
                    name = search.name,
                    date = LocalDateTime.now(),
                ),
            )
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(
                e,
            )
        }
    }
}
