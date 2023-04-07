package br.com.augusto.chucknorrisfacts.ui.fact.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.augusto.chucknorrisfacts.R
import br.com.augusto.chucknorrisfacts.databinding.FragmentFactsBinding
import br.com.augusto.chucknorrisfacts.ui.extensions.shareText
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.FactAdapter
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.FactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.FactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactUi
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.FactsUiState
import br.com.augusto.chucknorrisfacts.ui.fact.viewModel.FactsViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class FactsFragment : Fragment() {

    private var _binding: FragmentFactsBinding? = null
    private val binding get() = _binding!!
    private val factsViewModel: FactsViewModel by viewModel()
    private val factAdapter = FactAdapter(::onClickFact)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnInitScreen,
        )

        setupToolbar()
        setupViews()
        setupObservables()
    }

    private fun setupToolbar() {
        binding.toolbar.inflateMenu(R.menu.main_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.item_search -> {
                    factsViewModel.onNewUiEvent(
                        FactsUiEvent.OnClickSearchButton,
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun setupViews() {
        binding.factsList.adapter = factAdapter
    }

    private fun setupObservables() {
        factsViewModel.uiState.observe(viewLifecycleOwner) {
            updateUi(it)
        }

        factsViewModel.uiSideEffect.observe(viewLifecycleOwner) {
            handleSideEffect(it)
        }
    }

    private fun updateUi(factsUiState: FactsUiState) {
        with(binding) {
            toolbar.subtitle = factsUiState.search
            progressBar.isVisible = factsUiState.showLoading
            factsList.isVisible = factsUiState.showFactsList
            factAdapter.submitList(factsUiState.facts)
            textToSearchFacts.isVisible = factsUiState.showMessageToSearchFact
            textNoFactFound.isVisible = factsUiState.showMessageNoFactFound
        }
    }

    private fun handleSideEffect(factsUiSideEffect: FactsUiSideEffect?) {
        when (factsUiSideEffect) {
            is FactsUiSideEffect.Open.FactSharedDialog -> {
                openFactSharedDialog(factsUiSideEffect.factUi)
            }
            FactsUiSideEffect.NavigateTo.SearchScreen -> navigateToSearchScreen()
            null -> {}
        }
    }

    private fun openFactSharedDialog(factUi: FactUi) {
        shareText(factUi.url, getString(R.string.share_fact_url))
    }

    private fun navigateToSearchScreen() {
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnReceiveSearch(search = "Norris"),
        )
        Toast.makeText(requireContext(), "Navegar para tela de busca", Toast.LENGTH_SHORT).show()
    }

    private fun onClickFact(factUi: FactUi) {
        factsViewModel.onNewUiEvent(
            FactsUiEvent.OnClickToShareFact(factUi),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
