package com.project.HerbalEase.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.HerbalEase.data.remote.MockApiService
import com.project.HerbalEase.data.repo.AppRepository
import com.project.HerbalEase.ui.main.ui.home.HomeViewModel
import com.project.HerbalEase.ui.search.SearchViewModel

class ViewModelInjector(
//    private val context: Context
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val appRepository = AppRepository(MockApiService)

        return when (modelClass) {
            HomeViewModel::class.java -> HomeViewModel(
                appRepository
            ) as T

            SearchViewModel::class.java -> SearchViewModel(
                appRepository
            ) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}