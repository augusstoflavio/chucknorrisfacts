package br.com.augusto.chucknorrisfacts.ui.fact.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.augusto.chucknorrisfacts.builder.CategoryBuilder
import br.com.augusto.chucknorrisfacts.builder.SearchBuilder
import br.com.augusto.chucknorrisfacts.dispatcher.MainDispatcherRule
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.useCase.GetLatestSearchesUseCaseFake
import br.com.augusto.chucknorrisfacts.domain.useCase.GetRandomCategoriesUseCaseFake
import br.com.augusto.chucknorrisfacts.ui.extensions.awaitValue
import br.com.augusto.chucknorrisfacts.ui.fact.uiError.SearchFactsUiError
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.SearchFactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.SearchFactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchFactsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getRandomCategoriesUseCaseFake: GetRandomCategoriesUseCaseFake = GetRandomCategoriesUseCaseFake()
    private val getLatestSearchesUseCaseFake: GetLatestSearchesUseCaseFake = GetLatestSearchesUseCaseFake()
    private lateinit var searchFactsViewModel: SearchFactsViewModel

    @Before
    fun setup() {
        searchFactsViewModel = SearchFactsViewModel(
            getLatestSearchesUseCase = getLatestSearchesUseCaseFake,
            getRandomCategoriesUseCase = getRandomCategoriesUseCaseFake,
        )
    }

    @Test
    fun `should update lastSearches when load last searches successfully after init screen`() {
        getLatestSearchesUseCaseFake.result = Result.Success(
            listOf(
                SearchBuilder.buildModel(),
            ),
        )
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiState = searchFactsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState!!.lastSearches).isEqualTo(
            listOf(
                SearchBuilder.buildUi(),
            ),
        )
    }

    @Test
    fun `should update error when load last searches failed after init screen`() {
        val result = Result.Error.ApiError
        getLatestSearchesUseCaseFake.result = result
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiError = searchFactsViewModel.uiError.awaitValue()

        Truth.assertThat(uiError).isEqualTo(
            SearchFactsUiError(
                error = result,
                tryAgainUiEvent = SearchFactsUiEvent.OnClickTryAgainLoadLastSearches,
            ),
        )
    }

    @Test
    fun `should show loading when init screen`() {
        Dispatchers.setMain(StandardTestDispatcher())

        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiState = searchFactsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isTrue()
    }

    @Test
    fun `should hide loading when load last searches successfully after init screen`() {
        getLatestSearchesUseCaseFake.result = Result.Success(
            listOf(
                SearchBuilder.buildModel(),
            ),
        )

        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiState = searchFactsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    @Test
    fun `should hide loading when load last searches failed after init screen`() {
        val result = Result.Error.ApiError
        getLatestSearchesUseCaseFake.result = result

        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiState = searchFactsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    // ///

    @Test
    fun `should update categories when load categories successfully after init screen`() {
        getRandomCategoriesUseCaseFake.result = Result.Success(
            listOf(
                CategoryBuilder.buildModel(),
            ),
        )
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiState = searchFactsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState!!.categories).isEqualTo(
            listOf(
                CategoryBuilder.buildUi(),
            ),
        )
    }

    @Test
    fun `should update error when load categories failed after init screen`() {
        val result = Result.Error.ApiError
        getRandomCategoriesUseCaseFake.result = result
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiError = searchFactsViewModel.uiError.awaitValue()

        Truth.assertThat(uiError).isEqualTo(
            SearchFactsUiError(
                error = result,
                tryAgainUiEvent = SearchFactsUiEvent.OnClickTryAgainLoadCategories,
            ),
        )
    }

    @Test
    fun `should hide loading when load categories successfully after init screen`() {
        getRandomCategoriesUseCaseFake.result = Result.Success(
            listOf(
                CategoryBuilder.buildModel(),
            ),
        )

        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiState = searchFactsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    @Test
    fun `should hide loading when load categories failed after init screen`() {
        val result = Result.Error.ApiError
        getRandomCategoriesUseCaseFake.result = result
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )

        val uiState = searchFactsViewModel.uiState.awaitValue()

        Truth.assertThat(uiState?.showLoading).isFalse()
    }

    @Test
    fun `should navigate to previous screen with search when change search`() {
        searchFactsViewModel.onNewUiEvent(SearchFactsUiEvent.OnChangeSearch("my search"))

        val uiSideEffect = searchFactsViewModel.uiSideEffect.awaitValue()

        Truth.assertThat(uiSideEffect).isEqualTo(
            SearchFactsUiSideEffect.Navigate.ToPreviousScreenWithSearch(
                search = "my search",
            ),
        )
    }

    @Test
    fun `should navigate to previous screen with search when click on last search`() {
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnClickLastSearch(
                SearchUi(
                    name = "last search",
                ),
            ),
        )

        val uiSideEffect = searchFactsViewModel.uiSideEffect.awaitValue()

        Truth.assertThat(uiSideEffect).isEqualTo(
            SearchFactsUiSideEffect.Navigate.ToPreviousScreenWithSearch(
                search = "last search",
            ),
        )
    }

    @Test
    fun `should navigate to previous screen with search when click on category`() {
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnClickCategory(
                CategoryUi(
                    name = "category",
                ),
            ),
        )

        val uiSideEffect = searchFactsViewModel.uiSideEffect.awaitValue()

        Truth.assertThat(uiSideEffect).isEqualTo(
            SearchFactsUiSideEffect.Navigate.ToPreviousScreenWithSearch(
                search = "category",
            ),
        )
    }
}
