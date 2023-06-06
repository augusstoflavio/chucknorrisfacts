package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

class GetRandomCategoriesUseCaseFake : GetRandomCategoriesUseCase {

    var result: Result<List<Category>> = Result.Success(listOf())
    override suspend fun invoke(amount: Int): Result<List<Category>> {
        return result
    }
}