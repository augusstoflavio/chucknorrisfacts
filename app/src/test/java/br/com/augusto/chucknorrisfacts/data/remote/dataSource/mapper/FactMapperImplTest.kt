package br.com.augusto.chucknorrisfacts.data.remote.dataSource.mapper

import br.com.augusto.chucknorrisfacts.builder.FactBuilder
import com.google.common.truth.Truth
import org.junit.Test

class FactMapperImplTest {

    private val factMapperImpl = FactMapperImpl()

    @Test
    fun `should correctly map from FactResponse to fact`() {
        val expectedFact = FactBuilder.buildModel()

        val fact = factMapperImpl.fromFactResponse(FactBuilder.buildResponse())

        Truth.assertThat(fact).isEqualTo(expectedFact)
    }
}
