package br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.augusto.chucknorrisfacts.app.commons.INetworkState
import br.com.augusto.chucknorrisfacts.modules.fact.data.model.Fact
import br.com.augusto.chucknorrisfacts.modules.fact.data.repository.IFactRepository
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
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

    @Mock
    private lateinit var factsObserver: Observer<List<Fact>?>

    @Captor
    private lateinit var errorCaptor: ArgumentCaptor<String?>

    @Captor
    private lateinit var factsCaptor: ArgumentCaptor<List<Fact>?>

    @Test
    fun `when offline then set error`() {
        viewModel = FactsViewModel(factRepository, OfflineNetworkState())
        viewModel.error.observeForever(errorObserver)

        viewModel.searchFacts("word")

        Mockito.verify(errorObserver, Mockito.times(1))
            .onChanged(errorCaptor.capture())

        val values = errorCaptor.allValues
        Assert.assertFalse(values.isEmpty())
        Assert.assertEquals("Verifique a sua conexão", values[0])
    }

    @Test
    fun `when online then not set error`() {
        Mockito.`when`(factRepository.search("word"))
            .thenReturn(Single.just(listOf()))

        viewModel = FactsViewModel(factRepository, OnlineNetworkState())
        viewModel.error.observeForever(errorObserver)

        viewModel.searchFacts("word")

        Mockito.verify(errorObserver, Mockito.times(0))
            .onChanged(errorCaptor.capture())

        val values = errorCaptor.allValues
        Assert.assertTrue(values.isEmpty())
    }

    @Test
    fun `when the search number of characters is less than or equal 3 set error`() {
        viewModel = FactsViewModel(factRepository, OnlineNetworkState())
        viewModel.error.observeForever(errorObserver)

        viewModel.searchFacts("var")

        Mockito.verify(errorObserver, Mockito.times(1))
            .onChanged(errorCaptor.capture())

        val values = errorCaptor.allValues
        Assert.assertFalse(values.isEmpty())
        Assert.assertEquals("Preencha mais de 3 caracteres para realizar a busca", values[0])
    }

    @Test
    fun `when search set facts`() {
        val facts = listOf(
            Fact(listOf("BRAZIL", "INTERNET"), "FACT", "HTTP://FACT"),
            Fact(listOf("SCIENCE"), "FACT 2", "HTTP://FACT2"),
        )

        Mockito.`when`(factRepository.search("world"))
            .thenReturn(Single.just(facts))


        viewModel = FactsViewModel(factRepository, OnlineNetworkState())
        viewModel.facts.observeForever(factsObserver)

        viewModel.searchFacts("world")

        Mockito.verify(factsObserver, Mockito.times(1))
            .onChanged(factsCaptor.capture())

        val values = factsCaptor.allValues
        Assert.assertEquals(facts, values[0])
    }

    @Before
    fun setupMyTripViewModel() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler{ Schedulers.trampoline()}
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