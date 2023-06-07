package br.com.augusto.chucknorrisfacts.ui.fact.extensions

import br.com.augusto.chucknorrisfacts.builder.CategoryBuilder
import com.google.common.truth.Truth
import org.junit.Test

class CategoryExtensionsTest {

    @Test
    fun `should correctly convert of Category to CategoryUi`() {
        val expectedCategoryUi = CategoryBuilder.buildUi()

        val actualCategoryUi = CategoryBuilder.buildModel().toCategoryUi()

        Truth.assertThat(actualCategoryUi).isEqualTo(expectedCategoryUi)
    }
}