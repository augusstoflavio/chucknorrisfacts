package br.com.augusto.chucknorrisfacts.modules.fact.data.dao.category

import br.com.augusto.chucknorrisfacts.app.database.Database
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Category

class CategoryDao : ICategoryDao {

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