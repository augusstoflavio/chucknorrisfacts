package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.domain.repository.CategoryRepository

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(amount: Int): Result<List<Category>> {
        return categoryRepository.getCategories(amount)
    }
}
