package br.com.augusto.chucknorrisfacts.builder

import br.com.augusto.chucknorrisfacts.data.remote.response.FactResponse
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi
import br.com.augusto.chucknorrisfacts.ui.utils.TextUi

object FactBuilder {

    fun buildUi(): FactUi {
        return FactUi(
            description = TextUi.DirectString("description"),
            category = TextUi.DirectString("category 1"),
            descriptionSize = 25.0f,
            url = "https://fact.com/id",
        )
    }

    fun buildModel(): Fact {
        return Fact(
            categories = listOf("category 1", "category 2", "category 3"),
            value = "description",
            url = "https://fact.com/id",
        )
    }

    fun buildResponse(): FactResponse {
        return FactResponse(
            categories = listOf("category 1", "category 2", "category 3"),
            value = "description",
            url = "https://fact.com/id",
        )
    }
}
