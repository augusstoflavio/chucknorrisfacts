package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.FactDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Fact

class FactRepositoryImpl(
    private var factDataSource: FactDataSource
) : FactRepository {

    override suspend fun searchFacts(query: String): Result<List<Fact>> {
        return factDataSource.searchDataSource(query)
    }
}
