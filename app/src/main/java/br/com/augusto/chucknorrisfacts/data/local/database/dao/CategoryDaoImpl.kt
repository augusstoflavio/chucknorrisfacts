package br.com.augusto.chucknorrisfacts.data.local.database.dao

import br.com.augusto.chucknorrisfacts.data.local.database.Database
import br.com.augusto.chucknorrisfacts.domain.model.Category

class CategoryDaoImpl : CategoryDao {

    override fun findAll(): MutableList<Category>? {
        val realm = Database.getInstance()
        val categories = realm.copyFromRealm(
            realm.where(Category::class.java)
                .findAll()
        )
        realm.close()

        return categories
    }

    override fun save(categories: List<Category>) {
        val realm = Database.getInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(categories)
        realm.commitTransaction()
        realm.close()
    }
}
