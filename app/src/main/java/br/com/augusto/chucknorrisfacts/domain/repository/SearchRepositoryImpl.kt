package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.data.local.database.ChuckNorrisFactsDatabase
import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search
import java.time.LocalDateTime

class SearchRepositoryImpl(
    private val chuckNorrisFactsDatabase: ChuckNorrisFactsDatabase,
) : SearchRepository {

    override fun getLatestSearches(amount: Long): Result<List<Search>> {
        return Result.Success(listOf())
    }

    override suspend fun saveSearch(search: String) {
        chuckNorrisFactsDatabase.getSearchDao().insert(
            SearchEntity(
                name = search,
                date = LocalDateTime.now(),
            ),
        )
    }
}
