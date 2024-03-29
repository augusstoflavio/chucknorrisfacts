package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.domain.repository.CategoryRepository

class SyncCategoriesUseCaseImpl(
    private val categoryRepository: CategoryRepository
) : SyncCategoriesUseCase {

    override suspend operator fun invoke(): Result<List<Category>> {
        return categoryRepository.syncCategories()
    }
}