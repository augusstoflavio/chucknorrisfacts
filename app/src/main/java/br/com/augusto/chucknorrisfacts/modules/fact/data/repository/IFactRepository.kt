package br.com.augusto.chucknorrisfacts.modules.fact.data.repository

import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import io.reactivex.Observable

interface IFactRepository {
    fun search(query: String): Observable<List<Fact>>
    fun categories(): Observable<List<String>>
}