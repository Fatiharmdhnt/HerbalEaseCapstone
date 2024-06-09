package com.project.HerbalEase.ui.main.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.HerbalEase.R
import com.project.HerbalEase.databinding.FragmentHomeBinding
import com.project.HerbalEase.ui.adapter.HeadlineIngredientsAdapter
import com.project.HerbalEase.ui.detail.ingredients_detail.IngredientsDetailFragment
import com.project.HerbalEase.ui.factory.ViewModelInjector
import com.project.HerbalEase.ui.search.SearchFragment

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel> {
        ViewModelInjector()
    }

    private val headlineIngredientsAdapter = HeadlineIngredientsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        observeViewModel()

        setRecyclerViews()
        setListeners()

        return binding.root
    }

    private fun observeViewModel() {
        viewModel.apply {
            isLoadingIngredients.observe(viewLifecycleOwner) {
                if (it) {
                    // Handle ketika Loading disini
                } else {
                    // Handle ketika selesai Loading disini
                }
            }

            ingredientList.observe(viewLifecycleOwner) {
                // Handling list headline bahan
                headlineIngredientsAdapter.setData(it.toMutableList())
            }

            errorMessage.observe(viewLifecycleOwner) {
                // Handling error disini
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            searchBar.setOnClickListener {
                val bundle = Bundle()
                bundle.putBoolean(SearchFragment.IS_FROM_SEARCH, true)
                findNavController().navigate(R.id.navigation_search, bundle)
            }
        }
    }

    private fun setRecyclerViews() {
        binding.rvHeadlineIngredients.apply {
            headlineIngredientsAdapter.onItemClick = { ingredients ->
                val bundle = Bundle()
                bundle.putParcelable(IngredientsDetailFragment.EXTRA_INGREDIENTS, ingredients)
                findNavController().navigate(R.id.navigation_detail, bundle)
            }

            headlineIngredientsAdapter.onLoadMoreClick = { listIngredients ->
                // Handling klik load more disini, variable listIngredients isinya
                // semua ingredients, pass saja ke activity/fragment yang dituju
                findNavController().navigate(R.id.navigation_search)
            }

            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = headlineIngredientsAdapter
        }
    }
}