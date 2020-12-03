package br.com.augusto.chucknorrisfacts.modules.fact.data.repository

import br.com.augusto.chucknorrisfacts.app.database.Database
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import br.com.augusto.chucknorrisfacts.modules.fact.service.FactService
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

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
            saveInDatabase(it)
        }
    }

    private fun categoriesFromDatabase(): Single<List<Category>> {
        return Single.create {
            getCategoriesFromDatabase()
        }
    }

    private fun getCategoriesFromDatabase(): MutableList<Category>? {
        val realm = Database.getInstance()
        val categories = realm.copyFromRealm(
            realm.where(Category::class.java)
                .findAll()
        )
        realm.close()

        return categories
    }

    private fun saveInDatabase(categories: List<Category>) {
        val realm = Database.getInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(categories)
        realm.commitTransaction()
        realm.close()
    }
}