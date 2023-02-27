package br.com.augusto.chucknorrisfacts.data.local.dataSource

import br.com.augusto.chucknorrisfacts.data.local.database.dao.SearchDao
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.SearchDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Search
import java.util.Date

class SearchDataSourceImpl(
    private val searchDao: SearchDao
) : SearchDataSource {
    override fun saveSearch(query: String) {
        searchDao.save(
            Search().apply {
                name = query
                date = Date()
            }
        )
    }

    override fun getLatestSearches(amount: Long): Result<List<Search>> {
        return Result.Success(searchDao.geLatestSearches(amount))
    }
}
