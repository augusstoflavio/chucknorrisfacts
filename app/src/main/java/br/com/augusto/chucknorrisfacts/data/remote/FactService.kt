package br.com.augusto.chucknorrisfacts.data.remote

import br.com.augusto.chucknorrisfacts.data.remote.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FactService {

    @GET("jokes/categories")
    fun categories(): Response<List<String>>

    @GET("jokes/search")
    suspend fun searchFacts(@Query("query") query: String): Response<SearchResponse>
}
