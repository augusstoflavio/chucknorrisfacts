package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact

interface FactRepository {
    suspend fun searchFacts(query: String): Result<List<Fact>>
}
