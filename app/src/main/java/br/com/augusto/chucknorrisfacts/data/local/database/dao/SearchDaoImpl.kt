package br.com.augusto.chucknorrisfacts.data.local.database.dao

import br.com.augusto.chucknorrisfacts.data.local.database.Database
import br.com.augusto.chucknorrisfacts.domain.model.Search
import io.realm.Sort

class SearchDaoImpl : SearchDao {

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
