package br.com.augusto.chucknorrisfacts.data.dataSource

import br.com.augusto.chucknorrisfacts.data.extension.toSearch
import br.com.augusto.chucknorrisfacts.data.extension.toSearchEntity
import br.com.augusto.chucknorrisfacts.data.local.database.ChuckNorrisFactsDatabase
import br.com.augusto.chucknorrisfacts.data.util.safeCall
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Search

class SearchDataSourceImpl(
    private val chuckNorrisFactsDatabase: ChuckNorrisFactsDatabase,
) : SearchDataSource {

    override suspend fun getLatestSearches(amount: Int): Result<List<Search>> {
        return safeCall {
            val searches = chuckNorrisFactsDatabase.getSearchDao().get(
                amount,
            )
            searches.map {
                it.toSearch()
            }
        }
    }

    override suspend fun saveSearch(search: Search): Result<Unit> {
        return safeCall {
            chuckNorrisFactsDatabase.getSearchDao().insert(
                search.toSearchEntity(),
            )
        }
    }
}
