package br.com.augusto.chucknorrisfacts.modules.fact.service

import br.com.augusto.chucknorrisfacts.modules.fact.data.response.SearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FactService {

    @GET("jokes/categories")
    fun categories(): Observable<List<String>>

    @GET("jokes/search")
    fun searchFacts(@Query("query") query: String): Observable<SearchResponse>
}