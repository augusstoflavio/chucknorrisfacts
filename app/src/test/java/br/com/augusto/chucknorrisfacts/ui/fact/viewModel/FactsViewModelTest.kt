package br.com.augusto.chucknorrisfacts.ui.fact.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.augusto.chucknorrisfacts.builder.FactBuilder
import br.com.augusto.chucknorrisfacts.dispatcher.MainDispatcherRule
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.useCase.SearchFactsUseCaseFake
import br.com.augusto.chucknorrisfacts.ui.extensions.awaitValue
import br.com.augusto.chucknorrisfacts.ui.fact.uiError.FactsUiError
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.FactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.FactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi
import br.com.augusto.chucknorrisfacts.ui.utils.TextUi
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
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
    private var searchFactsUseCaseFake: SearchFactsUseCaseFake = SearchFactsUseCaseFake()

    @Before
    fun setup() {
        factsViewModel = FactsViewModel(
            searchFactsUseCase = searchFactsUseCaseFake,
        )
    }

    @Test
    fun `should navigate to search screen when click on search button`() {
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

    @Test
    fun `should update search when receive search`() {
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.search).isEqualTo("search")
    }

    @Test
    fun `should hide message to search fact when receive search`() {
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showMessageToSearchFact).isFalse()
    }

    @Test
    fun `should hide message no fact found when receive search`() {
        val dispatcher = StandardTestDispatcher()
        Dispatchers.setMain(dispatcher)
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showMessageNoFactFound).isFalse()
    }

    @Test
    fun `should show loading when receive search`() = runTest {
        Dispatchers.setMain(StandardTestDispatcher())
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isTrue()
    }

    @Test
    fun `should hide loading when search facts successfully`() {
        searchFactsUseCaseFake.result = Result.Success(
            listOf(
                FactBuilder.buildModel(),
            ),
        )
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    @Test
    fun `should hide loading when search facts failed`() {
        searchFactsUseCaseFake.result = Result.Error.NoConnectionError
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    @Test
    fun `should update facts when search facts successfully`() {
        searchFactsUseCaseFake.result = Result.Success(
            listOf(
                FactBuilder.buildModel(),
            ),
        )
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.facts).isEqualTo(
            listOf(FactBuilder.buildUi()),
        )
    }

    @Test
    fun `should show facts list when search facts successfully and facts is not empty`() {
        searchFactsUseCaseFake.result = Result.Success(
            listOf(
                FactBuilder.buildModel(),
            ),
        )
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showFactsList).isTrue()
    }

    @Test
    fun `should hide facts list when search facts successfully and facts is empty`() {
        searchFactsUseCaseFake.result = Result.Success(
            listOf(),
        )
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showFactsList).isFalse()
    }

    @Test
    fun `should hide message not found when search facts successfully and facts is not empty`() {
        searchFactsUseCaseFake.result = Result.Success(
            listOf(
                FactBuilder.buildModel(),
            ),
        )
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showMessageNoFactFound).isFalse()
    }

    @Test
    fun `should show message not found when search facts successfully and facts is empty`() {
        searchFactsUseCaseFake.result = Result.Success(
            listOf(),
        )
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiState = factsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showMessageNoFactFound).isTrue()
    }

    @Test
    fun `should update error when search facts failed`() {
        val result = Result.Error.NoConnectionError
        searchFactsUseCaseFake.result = result
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch("search"),
        )

        val uiError = factsViewModel.uiError.awaitValue()

        Truth.assertThat(uiError).isEqualTo(
            FactsUiError(
                error = result,
                tryAgainUiEvent = FactsUiEvent.OnClickTryAgainSearchFacts,
            ),
        )
    }
}
