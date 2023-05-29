package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact

interface SearchFactsUseCase {

    suspend operator fun invoke(search: String): Result<List<Fact>>
}