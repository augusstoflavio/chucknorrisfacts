package br.com.augusto.chucknorrisfacts.domain.useCase

import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Search

interface GetLatestSearchesUseCase {
    suspend operator fun invoke(): Result<List<Search>>
}