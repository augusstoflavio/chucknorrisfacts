package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.domain.repository.FactRepository
import br.com.augusto.chucknorrisfacts.domain.repository.SearchRepository

class SearchFactsUseCase(
    private val factRepository: FactRepository,
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(search: String): Result<List<Fact>> {
        searchRepository.saveSearch(search)
        return factRepository.searchFacts(search)
    }
}
