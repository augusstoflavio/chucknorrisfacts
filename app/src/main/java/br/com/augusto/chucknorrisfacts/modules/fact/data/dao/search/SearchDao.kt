package br.com.augusto.chucknorrisfacts.modules.fact.data.dao.search

import br.com.augusto.chucknorrisfacts.app.database.Database
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Search
import io.realm.Sort

class SearchDao : ISearchDao {

    override fun geLatestSearches(amount: Long): MutableList<Search> {
        val realm = Database.getInstance()
        val categories = realm.copyFromRealm(
            realm.where(Search::class.java)
                .sort("date", Sort.DESCENDING)
                .limit(amount)
                .findAll()
        )
        realm.close()

        return categories
    }

    override fun save(search: Search) {
        val realm = Database.getInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(search)
        realm.commitTransaction()
        realm.close()
    }
}
