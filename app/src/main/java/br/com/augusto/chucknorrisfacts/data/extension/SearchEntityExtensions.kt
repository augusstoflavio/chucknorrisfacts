package br.com.augusto.chucknorrisfacts.data.extension

import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity
import br.com.augusto.chucknorrisfacts.domain.model.Search

fun SearchEntity.toSearch(): Search {
    return Search(
        name = this.name,
    )
}
