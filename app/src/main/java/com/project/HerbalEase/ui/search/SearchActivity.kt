package com.project.HerbalEase.ui.search

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.HerbalEase.databinding.ActivitySearchBinding
import androidx.core.widget.addTextChangedListener
import com.project.HerbalEase.ui.adapter.SearchIngredientsAdapter
import com.project.HerbalEase.ui.detail.ingredients_detail.IngredientsDetailActivity
import com.project.HerbalEase.ui.factory.ViewModelInjector
import com.project.HerbalEase.utils.FakeData

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val isFromSearch by lazy { intent.getBooleanExtra(IS_FROM_SEARCH, false) }

    private val viewModel by viewModels<SearchViewModel> {
        ViewModelInjector()
    }

    private val searchIngredientsAdapter = SearchIngredientsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkIntentValues()
        observeViewModel()
        setSearchBar()
        setRecyclerView()
        setListeners()
    }

    private fun checkIntentValues() {
        binding.apply {
            svAccounts.hint =
                if (isFromSearch) "Keluhan apa yang kamu alami?" else "Cari Tanaman Herbal"
            searchIngredientsAdapter.isFromSearch = isFromSearch
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            isLoading.observe(this@SearchActivity) {
                if (it) {
                    // Handle ketika Loading disini
                } else {
                    // Handle ketika selesai Loading disini
                }
            }

            searchedIngredients.observe(this@SearchActivity) {
                searchIngredientsAdapter.submitList(it)
                binding.emptyView.isVisible = it.isEmpty()
            }

            errorMessage.observe(this@SearchActivity) {
                // Handling error disini
            }
        }
    }

    private fun setSearchBar() {
        binding.svAccounts.addTextChangedListener(onTextChanged = { query, _, _, _ ->
            if (query.isNullOrEmpty()) {
                viewModel.searchedIngredients.postValue(FakeData.generateIngredients())
            } else {
                viewModel.searchIngredients(query.toString(), isFromSearch)
            }
        })
    }

    private fun setRecyclerView() {
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchIngredientsAdapter
        }
    }

    private fun setListeners() {
        binding.apply {
            searchIngredientsAdapter.onIngredientsClick = { ingredients ->
                val iDetailIngredients =
                    Intent(this@SearchActivity, IngredientsDetailActivity::class.java)
                iDetailIngredients.putExtra(
                    IngredientsDetailActivity.EXTRA_INGREDIENTS,
                    ingredients
                )
                startActivity(iDetailIngredients)
            }

            btnBack.setOnClickListener { finish() }
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressbar.isVisible = isLoading
    }

    companion object {
        const val IS_FROM_SEARCH = "is_from_search"
    }
}