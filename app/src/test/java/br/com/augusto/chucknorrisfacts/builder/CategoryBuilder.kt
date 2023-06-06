package br.com.augusto.chucknorrisfacts.builder

import br.com.augusto.chucknorrisfacts.domain.model.Category
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi

object CategoryBuilder {

    fun buildUi(): CategoryUi {
        return CategoryUi(
            name = "Zelma Blevins",
        )
    }

    fun buildModel(): Category {
        return Category(
            name = "Zelma Blevins",
        )
    }
}
