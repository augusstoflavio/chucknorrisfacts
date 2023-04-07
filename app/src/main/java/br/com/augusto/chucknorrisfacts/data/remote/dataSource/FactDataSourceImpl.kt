package br.com.augusto.chucknorrisfacts.data.remote.dataSource

import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.data.remote.dataSource.mapper.FactMapper
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.FactDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Fact

class FactDataSourceImpl(
    private val factService: FactService,
    private val factMapper: FactMapper,
) : FactDataSource {

    override suspend fun searchDataSource(query: String): Result<List<Fact>> {
        return try {
            val data = factService.searchFacts(query)
            Result.Success(
                data.body()?.result?.map(factMapper::fromFactResponse) ?: listOf(),
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
