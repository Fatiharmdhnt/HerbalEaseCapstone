package com.project.HerbalEase.ui.main.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.project.HerbalEase.data.model.Ingredients
import com.project.HerbalEase.data.repo.AppRepository
import com.project.HerbalEase.utils.Result
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AppRepository) : ViewModel() {
    private val _isLoadingIngredients = MutableLiveData(false)
    val isLoadingIngredients: LiveData<Boolean> = _isLoadingIngredients

    private val _ingredientList = MutableLiveData(listOf<Ingredients>())
    val ingredientList: LiveData<List<Ingredients>> = _ingredientList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    init {
        getIngredientList()
    }

    private fun getIngredientList() {
        viewModelScope.launch {
            repository.getHeadlineIngredientsList().asFlow().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _isLoadingIngredients.postValue(true)
                    }

                    is Result.Success -> {
                        _isLoadingIngredients.postValue(false)
                        _ingredientList.postValue(result.data)
                    }

                    is Result.Error -> {
                        _isLoadingIngredients.postValue(false)
                        _errorMessage.postValue(result.error)
                    }
                }
            }
        }
    }
}