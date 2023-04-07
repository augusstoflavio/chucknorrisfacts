package br.com.augusto.chucknorrisfacts.data.remote.dataSource.mapper

import br.com.augusto.chucknorrisfacts.data.remote.response.FactResponse
import br.com.augusto.chucknorrisfacts.domain.model.Fact

interface FactMapper {

    fun fromFactResponse(factResponse: FactResponse): Fact
}
