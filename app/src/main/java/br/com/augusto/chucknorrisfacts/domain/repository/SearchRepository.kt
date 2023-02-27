package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search

interface SearchRepository {
    fun getLatestSearches(amount: Long): Result<List<Search>>
}
