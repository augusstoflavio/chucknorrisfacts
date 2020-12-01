package br.com.augusto.chucknorrisfacts.modules.fact.data.repository

import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import br.com.augusto.chucknorrisfacts.modules.fact.service.FactService
import io.reactivex.Observable

class FactRepository(private var factService: FactService) : IFactRepository {

    override fun search(query: String): Observable<List<Fact>> {
        return factService.searchFacts(query).map {
            it.result.map {
                Fact(
                    categories = it.categories,
                    value = it.value,
                    url = it.value
                )
            }
        }
    }

    override fun categories(): Observable<List<String>> {
        return factService.categories()
    }
}