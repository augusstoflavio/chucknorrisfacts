package br.com.augusto.chucknorrisfacts.data.remote.dataSource.mapper

import br.com.augusto.chucknorrisfacts.data.remote.response.FactResponse
import br.com.augusto.chucknorrisfacts.domain.model.Fact

class FactMapperImpl : FactMapper {

    override fun fromFactResponse(factResponse: FactResponse): Fact {
        return Fact(
            categories = factResponse.categories,
            value = factResponse.value,
            url = factResponse.url,
        )
    }
}
