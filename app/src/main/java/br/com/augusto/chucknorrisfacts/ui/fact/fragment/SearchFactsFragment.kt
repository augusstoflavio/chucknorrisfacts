package br.com.augusto.chucknorrisfacts.ui.fact.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.augusto.chucknorrisfacts.databinding.FragmentSearchFactsBinding
import br.com.augusto.chucknorrisfacts.ui.extensions.navigateUp
import br.com.augusto.chucknorrisfacts.ui.extensions.setNavigationResult
import br.com.augusto.chucknorrisfacts.ui.extensions.showError
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.CategoryAdapter
import br.com.augusto.chucknorrisfacts.ui.fact.adapter.SearchAdapter
import br.com.augusto.chucknorrisfacts.ui.fact.uiError.SearchFactsUiError
import br.com.augusto.chucknorrisfacts.ui.fact.uiEvent.SearchFactsUiEvent
import br.com.augusto.chucknorrisfacts.ui.fact.uiSideEffect.SearchFactsUiSideEffect
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.CategoryUi
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchFactsUiState
import br.com.augusto.chucknorrisfacts.ui.fact.uiState.SearchUi
import br.com.augusto.chucknorrisfacts.ui.fact.viewModel.SearchFactsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFactsFragment : Fragment() {

    private var _binding: FragmentSearchFactsBinding? = null
    private val binding get() = _binding!!

    private val searchFactsViewModel: SearchFactsViewModel by viewModel()

    private val categoriesAdapter = CategoryAdapter(::onClickCategory)
    private val searchAdapter = SearchAdapter(::onClickSearch)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchFactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInput()
        setupLists()
        setupObservers()

        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnInitScreen,
        )
    }

    private fun setupInput() {
        binding.search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId != EditorInfo.IME_ACTION_DONE) {
                return@setOnEditorActionListener false
            }

            searchFactsViewModel.onNewUiEvent(
                SearchFactsUiEvent.OnChangeSearch(
                    binding.search.text.toString(),
                ),
            )

            true
        }
    }

    private fun setupLists() {
        with(binding) {
            suggestions.layoutManager = GridLayoutManager(requireContext(), 3)
            suggestions.adapter = categoriesAdapter
            lastSearchs.adapter = searchAdapter
        }
    }

    private fun setupObservers() {
        with(searchFactsViewModel) {
            uiState.observe(viewLifecycleOwner) {
                it?.let { uiState ->
                    syncUiState(uiState)
                }
            }

            uiError.observe(viewLifecycleOwner) {
                it?.let { uiError ->
                    handleError(uiError)
                }
            }

            uiSideEffect.observe(viewLifecycleOwner) {
                it?.let { uiSideEffect ->
                    handleSideEffect(uiSideEffect)
                }
            }
        }
    }

    private fun syncUiState(uiState: SearchFactsUiState) {
        searchAdapter.submitList(uiState.lastSearches)
        categoriesAdapter.submitList(uiState.categories)
        binding.progressBar.isVisible = uiState.showLoading
    }

    private fun handleError(uiError: SearchFactsUiError) {
        requireContext().showError(uiError.error) {
            searchFactsViewModel.onNewUiEvent(uiError.tryAgainUiEvent)
        }
    }

    private fun handleSideEffect(uiSideEffect: SearchFactsUiSideEffect) {
        when (uiSideEffect) {
            is SearchFactsUiSideEffect.Navigate -> handleNavigate(uiSideEffect)
        }
    }

    private fun handleNavigate(uiSideEffect: SearchFactsUiSideEffect.Navigate) {
        when (uiSideEffect) {
            is SearchFactsUiSideEffect.Navigate.ToPreviousScreenWithSearch -> {
                navigateToPreviousScreenWithSearch(uiSideEffect)
            }
        }
    }

    private fun navigateToPreviousScreenWithSearch(
        uiSideEffect: SearchFactsUiSideEffect.Navigate.ToPreviousScreenWithSearch,
    ) {
        setNavigationResult(SEARCH_FACTS_RESULT_KEY, uiSideEffect.search)
        navigateUp()
    }

    private fun onClickCategory(categoryUi: CategoryUi) {
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnClickCategory(categoryUi),
        )
    }

    private fun onClickSearch(searchUi: SearchUi) {
        searchFactsViewModel.onNewUiEvent(
            SearchFactsUiEvent.OnClickLastSearch(searchUi),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val SEARCH_FACTS_RESULT_KEY = "SEARCH"
    }
}
