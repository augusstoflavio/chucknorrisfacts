package br.com.augusto.chucknorrisfacts.data.remote.extension

import br.com.augusto.chucknorrisfacts.data.remote.response.FactResponse
import br.com.augusto.chucknorrisfacts.domain.model.Fact

fun FactResponse.toFact(): Fact {
    return Fact(
        categories = this.categories,
        value = this.value,
        url = this.url,
    )
}
