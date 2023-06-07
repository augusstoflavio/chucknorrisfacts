package br.com.augusto.chucknorrisfacts.ui.fact.extensions

import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.builder.FactBuilder
import br.com.augusto.chucknorrisfacts.ui.utils.TextUi
import com.google.common.truth.Truth
import org.junit.Test

class FactExtensionsTest {

    @Test
    fun `should correctly convert of Fact to FactUi`() {
        val expectedFactUi = FactBuilder.buildUi()

        val actualFactUi = FactBuilder.buildModel().toFactUi()

        Truth.assertThat(actualFactUi).isEqualTo(expectedFactUi)
    }

    @Test
    fun `should fill category with first category when fact has category`() {
        val fact = FactBuilder.buildModel().copy(
            categories = listOf(
                "category 1",
                "category 2",
            ),
        )

        val factUi = fact.toFactUi()

        Truth.assertThat(factUi.category).isEqualTo(
            TextUi.DirectString("category 1"),
        )
    }

    @Test
    fun `should fill category with text uncategorized when fact has not category`() {
        val fact = FactBuilder.buildModel().copy(
            categories = listOf(),
        )

        val factUi = fact.toFactUi()

        Truth.assertThat(factUi.category).isEqualTo(
            TextUi.StringResource(R.string.uncategorized),
        )
    }

    @Test
    fun `should fill description with 18F when fact value size is greater than 80`() {
        val fact = FactBuilder.buildModel().copy(
            value = "a".repeat(81),
        )

        val factUi = fact.toFactUi()

        Truth.assertThat(factUi.descriptionSize).isEqualTo(
            18F,
        )
    }

    @Test
    fun `should fill description with 25F when fact value size is equal to 80`() {
        val fact = FactBuilder.buildModel().copy(
            value = "a".repeat(80),
        )

        val factUi = fact.toFactUi()

        Truth.assertThat(factUi.descriptionSize).isEqualTo(
            25F,
        )
    }

    @Test
    fun `should fill description with 25F when fact value size is less than 80`() {
        val fact = FactBuilder.buildModel().copy(
            value = "a".repeat(79),
        )

        val factUi = fact.toFactUi()

        Truth.assertThat(factUi.descriptionSize).isEqualTo(
            25F,
        )
    }
}
