package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search

interface SearchRepository {
    suspend fun getLatestSearches(amount: Int): Result<List<Search>>
    suspend fun saveSearch(search: Search): Result<Unit>
}
