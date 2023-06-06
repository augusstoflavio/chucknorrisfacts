package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search
import br.com.augusto.chucknorrisfacts.domain.repository.SearchRepository

class GetLatestSearchesUseCaseImpl(
    private val searchRepository: SearchRepository
) : GetLatestSearchesUseCase {

    override suspend operator fun invoke(): Result<List<Search>> {
        return searchRepository.getLatestSearches(10)
    }
}
