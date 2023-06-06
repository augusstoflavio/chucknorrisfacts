package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search

class GetLatestSearchesUseCaseFake : GetLatestSearchesUseCase {

    var result: Result<List<Search>> = Result.Success(listOf())
    override suspend fun invoke(): Result<List<Search>> {
        return result
    }
}
