package br.com.augusto.chucknorrisfacts.data.local.database.dao

import br.com.augusto.chucknorrisfacts.domain.model.Search

interface SearchDao {
    fun geLatestSearches(amount: Long): MutableList<Search>
    fun save(search: Search)
}
