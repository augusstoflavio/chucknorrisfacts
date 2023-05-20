package br.com.augusto.chucknorrisfacts.domain.dataSource

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact

interface FactDataSource {

    suspend fun searchFacts(query: String): Result<List<Fact>>
}