package br.com.augusto.chucknorrisfacts.data.remote.dataSource

import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.dataSource.FactDataSource
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FactDataSourceImpl(private val factService: FactService) : FactDataSource {

    override suspend fun searchDataSource(query: String): Result<List<Fact>> {
        return withContext(Dispatchers.IO) {
            try {
                factService.searchFacts(query)
                Result.Success(
                    listOf()
                )
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}