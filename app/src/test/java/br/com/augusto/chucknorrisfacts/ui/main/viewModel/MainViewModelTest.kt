package br.com.augusto.chucknorrisfacts.ui.main.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.augusto.chucknorrisfacts.dispatcher.MainDispatcherRule
import br.com.augusto.chucknorrisfacts.domain.Result
import br.com.augusto.chucknorrisfacts.domain.useCase.SyncCategoriesUseCaseFake
import br.com.augusto.chucknorrisfacts.ui.extensions.awaitValue
import br.com.augusto.chucknorrisfacts.ui.main.uiError.MainUiError
import br.com.augusto.chucknorrisfacts.ui.main.uiEvent.MainUiEvent
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val syncCategoriesUseCase = SyncCategoriesUseCaseFake()
    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setup() {
        mainViewModel = MainViewModel(
            syncCategoriesUseCase = syncCategoriesUseCase,
        )
    }

    @Test
    fun `should update error when sync categories failed`() = runTest {
        val error = Result.Error.NoConnectionError
        syncCategoriesUseCase.result = Result.Error.NoConnectionError

        mainViewModel.onUiEvent(MainUiEvent.OnInitScreen)

        val uiError = mainViewModel.uiError.awaitValue()

        Truth.assertThat(uiError).isEqualTo(
            MainUiError(
                error = error,
                tryAgainUiEvent = MainUiEvent.OnClickTryAgainErrorDialogButton,
            ),
        )
    }

    @Test
    fun `should update error when click on try again error dialog button sync categories failed`() = runTest {
        val error = Result.Error.NoConnectionError
        syncCategoriesUseCase.result = Result.Error.NoConnectionError

        mainViewModel.onUiEvent(MainUiEvent.OnClickTryAgainErrorDialogButton)

        val uiError = mainViewModel.uiError.awaitValue()

        Truth.assertThat(uiError).isEqualTo(
            MainUiError(
                error = error,
                tryAgainUiEvent = MainUiEvent.OnClickTryAgainErrorDialogButton,
            ),
        )
    }
}
