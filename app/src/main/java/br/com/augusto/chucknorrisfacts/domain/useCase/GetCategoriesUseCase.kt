package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoriesUseCase(private val categoryRepository: CategoryRepository) {

    suspend operator fun invoke(): Flow<Result<List<Category>>> {
        return categoryRepository.getCategories()
    }
}
