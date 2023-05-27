package br.com.augusto.chucknorrisfacts.ui.fact.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.augusto.chucknorrisfacts.dispatcher.MainDispatcherRule
import br.com.augusto.chucknorrisfacts.domain.useCase.SearchFactsUseCase
import br.com.augusto.chucknorrisfacts.domain.useCase.SearchFactsUseCaseFake
import br.com.augusto.chucknorrisfacts.ui.extensions.awaitValue
import br.com.augusto.chucknorrisfacts.ui.fact.mapper.FactUiMapper
import br.com.augusto.chucknorrisfacts.ui.fact.mapper.FactUiMapperImpl
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.FactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.FactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi
import br.com.augusto.chucknorrisfacts.ui.utils.TextUi
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class FactsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var factsViewModel: FactsViewModel
    private var factUiMapper: FactUiMapper = FactUiMapperImpl()
    private var searchFactsUseCaseFake: SearchFactsUseCase = SearchFactsUseCaseFake()

    @Before
    fun setup() {
        factsViewModel = FactsViewModel(
            searchFactsUseCase = searchFactsUseCaseFake,
            factUiMapper = factUiMapper,
        )
    }

    @Test
    fun `should navigate to search screen when click on search button`() = runTest {
        factsViewModel.onNewUiEvent(FactsUiEvent.OnClickSearchButton)

        val uiSideEffect = factsViewModel.uiSideEffect.awaitValue()

        Truth.assertThat(uiSideEffect).isEqualTo(
            FactsUiSideEffect.NavigateTo.SearchScreen,
        )
    }

    @Test
    fun `should open the fact shared dialog when click on share fact button`() {
        val factUi = FactUi(
            description = TextUi.DirectString("description"),
            category = TextUi.DirectString("category"),
            descriptionSize = 0.0f,
            url = "http://url.com.br",
        )

        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnClickShareFactButton(factUi),
        )

        val uiSideEffect = factsViewModel.uiSideEffect.awaitValue()

        Truth.assertThat(uiSideEffect).isEqualTo(
            FactsUiSideEffect.Open.FactSharedDialog(factUi),
        )
    }
}
