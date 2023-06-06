package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

interface GetRandomCategoriesUseCase {
    suspend operator fun invoke(amount: Int): Result<List<Category>>
}