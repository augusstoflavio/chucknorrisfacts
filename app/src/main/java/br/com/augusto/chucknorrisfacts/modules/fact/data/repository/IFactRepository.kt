package br.com.augusto.chucknorrisfacts.modules.fact.data.repository

import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Search
import io.reactivex.Flowable
import io.reactivex.Single

interface IFactRepository {
    fun search(query: String): Single<List<Fact>>
    fun categories(): Flowable<List<Category>>
    fun lastSearchs(): Single<List<Search>>
}