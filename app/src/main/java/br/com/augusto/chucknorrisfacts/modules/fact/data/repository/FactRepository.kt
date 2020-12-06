package br.com.augusto.chucknorrisfacts.modules.fact.data.repository

import br.com.augusto.chucknorrisfacts.modules.fact.data.dao.category.ICategoryDao
import br.com.augusto.chucknorrisfacts.modules.fact.data.dao.search.ISearchDao
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Search
import br.com.augusto.chucknorrisfacts.modules.fact.service.FactService
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.*

class FactRepository(
    private var factService: FactService,
    private var categoryDao: ICategoryDao,
    private val searchDao: ISearchDao
) : IFactRepository {

    override fun search(query: String): Single<List<Fact>> {
        saveSearch(query)

        return factService.searchFacts(query).map {
            it.result.map {
                Fact(
                    categories = it.categories,
                    value = it.value,
                    url = it.url
                )
            }
        }
    }

    private fun saveSearch(query: String) {
        val search = Search()
        search.name = query
        search.date = Date()
        searchDao.save(search)
    }

    override fun categories(): Flowable<List<Category>> {
        return Single.merge(
            categoriesFromDatabase(),
            categoriesFromApi()
        )
    }

    private fun categoriesFromApi(): Single<List<Category>> {
        return factService.categories().map { it ->
            it.map {
                val categorie = Category()
                categorie.name = it
                return@map categorie
            }
        }.doOnSuccess {
            categoryDao.save(it)
        }
    }

    private fun categoriesFromDatabase(): Single<List<Category>> {
        return Single.just(
            categoryDao.findAll()
        )
    }

    override fun lastestSearchs(amount: Long): Single<List<Search>> {
        return Single.just(searchDao.geLatestSearches(amount))
    }
}