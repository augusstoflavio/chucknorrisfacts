package br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.augusto.chucknorrisfacts.app.commons.INetworkState
import br.com.augusto.chucknorrisfacts.modules.fact.data.repository.IFactRepository
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FactsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FactsViewModel

    @Mock
    private lateinit var factRepository: IFactRepository

    @Mock
    private lateinit var errorObserver: Observer<String?>

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<String?>

    @Test
    fun `when offline then set error`() {
        viewModel = FactsViewModel(factRepository, OfflineNetworkState())
        viewModel.error.observeForever(errorObserver)

        viewModel.searchFacts("word")

        Mockito.verify(errorObserver, Mockito.times(1))
            .onChanged(argumentCaptor.capture())

        val values = argumentCaptor.allValues
        Assert.assertFalse(values.isEmpty())
        Assert.assertEquals("Verifique a sua conexão", values[0])
    }

    @Test
    fun `when the search number of characters is less than or equal 3 set error`() {
        viewModel = FactsViewModel(factRepository, OnlineNetworkState())
        viewModel.error.observeForever(errorObserver)

        viewModel.searchFacts("var")

        Mockito.verify(errorObserver, Mockito.times(1))
            .onChanged(argumentCaptor.capture())

        val values = argumentCaptor.allValues
        Assert.assertFalse(values.isEmpty())
        Assert.assertEquals("Preencha mais de 3 caracteres para realizar a busca", values[0])
    }

    @Before
    fun setupMyTripViewModel() {
        RxJavaPlugins.setIoSchedulerHandler{ Schedulers.trampoline()}
    }
}

class OfflineNetworkState: INetworkState {

    override fun isConnected(): Boolean {
        return false
    }
}

class OnlineNetworkState: INetworkState {

    override fun isConnected(): Boolean {
        return true
    }
}