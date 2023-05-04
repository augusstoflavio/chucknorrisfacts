package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search

class SearchRepositoryImpl() : SearchRepository {

    override fun getLatestSearches(amount: Long): Result<List<Search>> {
        return Result.Success(listOf())
    }
}
