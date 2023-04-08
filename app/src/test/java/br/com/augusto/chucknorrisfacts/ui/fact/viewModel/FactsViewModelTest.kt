package br.com.augusto.chucknorrisfacts.ui.fact.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.augusto.chucknorrisfacts.builder.FactBuilder
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.model.Fact
import br.com.augusto.chucknorrisfacts.domain.repository.FactRepository
import br.com.augusto.chucknorrisfacts.domain.useCase.SearchFactsUseCase
import br.com.augusto.chucknorrisfacts.ui.fact.extensions.getTestValue
import br.com.augusto.chucknorrisfacts.ui.fact.mapper.FactUiMapperImpl
import br.com.augusto.chucknorrisfacts.ui.fact.uiError.FactsUiError
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.FactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.FactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.util.MainCoroutineRule
import com.google.common.truth.Truth
import kotlinx.coroutines.test.pauseDispatcher
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

class FactRepositoryFake : FactRepository {
    var searchFactsResult: Result<List<Fact>> = Result.Success(
        listOf(),
    )
    var searchFactsWasCalled = false
    override suspend fun searchFacts(query: String): Result<List<Fact>> {
        searchFactsWasCalled = true
        return searchFactsResult
    }
}

class FactsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var factsViewModel: FactsViewModel
    private lateinit var factRepository: FactRepositoryFake

    private fun setupViewModel() {
        factRepository = FactRepositoryFake()
        factsViewModel = FactsViewModel(
            searchFactsUseCase = SearchFactsUseCase(factRepository),
            factUiMapper = FactUiMapperImpl(),
        )
    }

    @Test
    fun `should display the message for the user to fetch the facts when starting the screen`() {
        setupViewModel()

        factsViewModel.onNewUiEvent(FactsUiEvent.OnInitScreen)

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showMessageToSearchFact).isTrue()
    }

    @Test
    fun `should hide the message for the user to search for facts when receives a search`() {
        setupViewModel()

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showMessageToSearchFact).isFalse()
    }

    @Test
    fun `should hide the facts list when starting the screen`() {
        setupViewModel()

        factsViewModel.onNewUiEvent(FactsUiEvent.OnInitScreen)

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showFactsList).isFalse()
    }

    @Test
    fun `should show the facts list when receives a search`() {
        setupViewModel()

        mainCoroutineRule.pauseDispatcher()

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showFactsList).isTrue()
    }

    @Test
    fun `should navigate to the search screen when clicking the search button`() {
        setupViewModel()

        factsViewModel.onNewUiEvent(FactsUiEvent.OnClickSearchButton)

        val uiSideEffect = factsViewModel.uiSideEffect.getTestValue()

        Truth.assertThat(uiSideEffect).isEqualTo(FactsUiSideEffect.NavigateTo.SearchScreen)
    }

    @Test
    fun `should fetch the facts when updating the fetch`() {
    }

    @Test
    fun `should show loading when starting to fetch facts`() {
        setupViewModel()

        mainCoroutineRule.pauseDispatcher()
        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showLoading).isTrue()
    }

    @Test
    fun `should hide loading when fetching facts successfully`() {
        setupViewModel()

        factRepository.searchFactsResult = Result.Success(
            listOf(),
        )

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    @Test
    fun `should hide loading when fetching failed facts`() {
        setupViewModel()

        factRepository.searchFactsResult = Result.Error(
            Exception(),
        )

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    @Test
    fun `should update the error when it fails to query the facts`() {
        setupViewModel()

        val error = Result.Error(
            Exception(),
        )
        factRepository.searchFactsResult = error

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiError = factsViewModel.uiError.getTestValue()

        Truth.assertThat(uiError).isEqualTo(
            FactsUiError(
                tryAgainUiEvent = FactsUiEvent.OnClickTryAgainSearchFacts,
                error = error,
            ),
        )
    }

    @Test
    fun `should update the list of facts when successfully querying the facts`() {
        setupViewModel()

        factRepository.searchFactsResult = Result.Success(
            listOf(
                FactBuilder.buildModel(),
            ),
        )

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.facts).isEqualTo(
            listOf(
                FactBuilder.buildUi(),
            ),
        )
    }

    @Test
    fun `should display the list of facts when it has facts for the query`() {
        setupViewModel()

        factRepository.searchFactsResult = Result.Success(
            listOf(
                FactBuilder.buildModel(),
            ),
        )

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showFactsList).isTrue()
    }

    @Test
    fun `should hide the list of facts when it has no facts for the query`() {
        setupViewModel()

        factRepository.searchFactsResult = Result.Success(
            listOf(),
        )

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showFactsList).isFalse()
    }

    @Test
    fun `should display a message informing that there is no fact corresponding to the search when the query is successful and the list is empty`() {
        setupViewModel()

        factRepository.searchFactsResult = Result.Success(
            listOf(),
        )

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showMessageNoFactFound).isTrue()
    }

    @Test
    fun `should hide the message informing that there is no fact corresponding to the search when the list is not empty`() {
        setupViewModel()

        factRepository.searchFactsResult = Result.Success(
            listOf(
                FactBuilder.buildModel(),
            ),
        )

        factsViewModel.onNewUiEvent(FactsUiEvent.OnReceiveSearch("search"))

        val uiState = factsViewModel.uiState.getTestValue()

        Truth.assertThat(uiState?.showMessageNoFactFound).isFalse()
    }

    @Test
    fun `should open fact sharing modal when clicking share button`() {
        setupViewModel()

        factsViewModel.onNewUiEvent(FactsUiEvent.OnClickToShareFact(FactBuilder.buildUi()))

        val uiSideEffect = factsViewModel.uiSideEffect.getTestValue()

        Truth.assertThat(uiSideEffect).isEqualTo(
            FactsUiSideEffect.Open.FactSharedDialog(FactBuilder.buildUi()),
        )
    }
}
