package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category

interface SyncCategoriesUseCase {
    suspend operator fun invoke(): Result<List<Category>>
}