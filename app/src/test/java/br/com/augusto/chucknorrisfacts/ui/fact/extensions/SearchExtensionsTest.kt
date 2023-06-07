package br.com.augusto.chucknorrisfacts.ui.fact.extensions

import br.com.augusto.chucknorrisfacts.builder.SearchBuilder
import com.google.common.truth.Truth
import org.junit.Test

class SearchExtensionsTest {

    @Test
    fun `should correctly convert of Search to SearchUi`() {
        val expectedSearchUi = SearchBuilder.buildUi()

        val actualSearchUi = SearchBuilder.buildModel().toSearchUi()

        Truth.assertThat(actualSearchUi).isEqualTo(expectedSearchUi)
    }
}