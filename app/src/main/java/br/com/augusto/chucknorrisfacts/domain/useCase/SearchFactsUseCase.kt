package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.domain.repository.FactRepository

class SearchFactsUseCase(private val factRepository: FactRepository) {

    suspend operator fun invoke(search: String): Result<List<Fact>> {
        //TODO salvar as buscas
        return factRepository.searchFacts(search)
    }
}
