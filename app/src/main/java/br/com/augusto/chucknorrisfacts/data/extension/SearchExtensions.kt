package br.com.augusto.chucknorrisfacts.data.extension

import br.com.augusto.chucknorrisfacts.data.local.entity.SearchEntity
import br.com.augusto.chucknorrisfacts.domain.model.Search
import java.time.LocalDateTime

fun Search.toSearchEntity(): SearchEntity {
    return SearchEntity(
        name = this.name,
        date = LocalDateTime.now(),
    )
}