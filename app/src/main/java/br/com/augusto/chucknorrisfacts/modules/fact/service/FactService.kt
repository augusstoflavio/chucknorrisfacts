package br.com.augusto.chucknorrisfacts.modules.fact.service

import br.com.augusto.chucknorrisfacts.modules.fact.data.response.SearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FactService {

    @GET("jokes/categories")
    fun categories(): Single<List<String>>

    @GET("jokes/search")
    fun searchFacts(@Query("query") query: String): Observable<SearchResponse>
}