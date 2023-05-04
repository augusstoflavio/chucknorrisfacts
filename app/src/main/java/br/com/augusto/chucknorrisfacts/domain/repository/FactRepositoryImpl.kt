package br.com.augusto.chucknorrisfacts.domain.repository

import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.data.remote.extension.toFact
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact

class FactRepositoryImpl(
    private val factService: FactService
) : FactRepository {

    override suspend fun searchFacts(query: String): Result<List<Fact>> {
        return try {
            val data = factService.searchFacts(query)
            Result.Success(
                data.body()?.result?.map {
                    it.toFact()
                } ?: listOf(),
            )
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
