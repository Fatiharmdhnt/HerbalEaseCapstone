package com.project.HerbalEase.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.project.HerbalEase.data.repo.AppRepository
import com.project.HerbalEase.utils.FakeData
import com.project.HerbalEase.utils.Result
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: AppRepository) : ViewModel() {
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val searchedIngredients = MutableLiveData(FakeData.generateIngredients())

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun searchIngredients(query: String, isFromSearch: Boolean) {
        viewModelScope.launch {
            val response = if (isFromSearch) {
                repository.searchKeluhanIngredients(query)
            } else {
                repository.searchIngredients(query)
            }

            response.asFlow().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _isLoading.postValue(true)
                    }

                    is Result.Success -> {
                        _isLoading.postValue(false)
                        searchedIngredients.postValue(result.data)
                    }

                    is Result.Error -> {
                        _isLoading.value = false
                        _errorMessage.value = result.error
                    }
                }
            }
        }
    }
}