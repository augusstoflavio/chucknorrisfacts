package br.com.augusto.chucknorrisfacts.ui.fact.mapper

import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.builder.FactBuilder
import br.com.augusto.chucknorrisfacts.ui.utils.TextUi
import com.google.common.truth.Truth
import org.junit.Test

class FactUiMapperImplTest {

    private val factUiMapperImpl = FactUiMapperImpl()

    @Test
    fun `should correctly map from Fact to FactUI`() {
        val expectedFactUi = FactBuilder.buildUi()
        val fact = FactBuilder.buildModel()

        val factUi = factUiMapperImpl.fromModel(fact)

        Truth.assertThat(factUi).isEqualTo(expectedFactUi)
    }

    @Test
    fun `should populate the category field with the first category in the list`() {
        val fact = FactBuilder.buildModel().copy(
            categories = listOf("category A", "Category B"),
        )

        val factUi = factUiMapperImpl.fromModel(fact)

        Truth.assertThat(factUi.category).isEqualTo(TextUi.DirectString("category A"))
    }

    @Test
    fun `should fill the category field with the text uncategorized if it has no category`() {
        val fact = FactBuilder.buildModel().copy(
            categories = listOf(),
        )

        val factUi = factUiMapperImpl.fromModel(fact)

        Truth.assertThat(factUi.category).isEqualTo(TextUi.StringResource(R.string.uncategorized))
    }

    @Test
    fun `the length of the description should be 18 if the description has more than 80 characters`() {
        val fact = FactBuilder.buildModel().copy(
            value = "a".repeat(81),
        )

        val factUi = factUiMapperImpl.fromModel(fact)

        Truth.assertThat(factUi.descriptionSize).isEqualTo(18F)
    }

    @Test
    fun `the length of the description should be 25 if the description has less than 80 characters`() {
        val fact = FactBuilder.buildModel().copy(
            value = "a".repeat(79),
        )

        val factUi = factUiMapperImpl.fromModel(fact)

        Truth.assertThat(factUi.descriptionSize).isEqualTo(25F)
    }

    @Test
    fun `the length of the description should be 25 if the description has 80 characters`() {
        val fact = FactBuilder.buildModel().copy(
            value = "a".repeat(80),
        )

        val factUi = factUiMapperImpl.fromModel(fact)

        Truth.assertThat(factUi.descriptionSize).isEqualTo(25F)
    }
}
