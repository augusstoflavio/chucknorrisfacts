package br.com.augusto.chucknorrisfacts.modules.fact.data.dao.search

import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Search

interface ISearchDao {
    fun geLatestSearches(amount: Long): MutableList<Search>
    fun save(search: Search)
}
