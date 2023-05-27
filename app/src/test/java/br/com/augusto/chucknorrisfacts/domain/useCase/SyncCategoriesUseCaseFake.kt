package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

class SyncCategoriesUseCaseFake : SyncCategoriesUseCase {

    var result: Result<List<Category>> = Result.Success(
        listOf(),
    )
    override suspend fun invoke(): Result<List<Category>> {
        return result
    }
}
