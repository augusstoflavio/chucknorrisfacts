package br.com.augusto.chucknorrisfacts.data.dataSource

import br.com.augusto.chucknorrisfacts.data.local.database.ChuckNorrisFactsDatabase
import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity
import br.com.augusto.chucknorrisfacts.data.util.safeCall
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Search
import java.time.LocalDateTime

class SearchDataSourceImpl(
    private val chuckNorrisFactsDatabase: ChuckNorrisFactsDatabase,
) : SearchDataSource {
    override suspend fun getLatestSearches(amount: Int): Result<List<Search>> {
        return safeCall {
            val searches = chuckNorrisFactsDatabase.getSearchDao().get(
                amount,
            )
            searches.map {
                Search(
                    name = it.name,
                )
            }
        }
    }

    override suspend fun saveSearch(search: Search): Result<Unit> {
        return safeCall {
            chuckNorrisFactsDatabase.getSearchDao().insert(
                SearchEntity(
                    name = search.name,
                    date = LocalDateTime.now(),
                ),
            )
        }
    }
}
