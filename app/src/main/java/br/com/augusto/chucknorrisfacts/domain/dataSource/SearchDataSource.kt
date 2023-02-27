package br.com.augusto.chucknorrisfacts.domain.dataSource

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search

interface SearchDataSource {
    fun saveSearch(query: String)
    fun getLatestSearches(amount: Long): Result<List<Search>>
}
