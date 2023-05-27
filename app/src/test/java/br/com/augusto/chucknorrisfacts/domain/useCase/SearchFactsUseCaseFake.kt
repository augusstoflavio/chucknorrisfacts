package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact

class SearchFactsUseCaseFake : SearchFactsUseCase {

    var result: Result<List<Fact>> = Result.Success(
        listOf(),
    )

    override suspend fun invoke(search: String): Result<List<Fact>> {
        return result
    }
}
