package com.project.HerbalEase.data.repo

import androidx.lifecycle.liveData
import com.project.HerbalEase.data.remote.MockApiService
import com.project.HerbalEase.utils.Result

class AppRepository(
    private val apiService: MockApiService
) {
    fun getHeadlineIngredientsList() = liveData {
        emit(Result.Loading)
        try {
            val ingredientsResponse = apiService.getHeadlineIngredients()
            emit(Result.Success(ingredientsResponse))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchIngredients(query: String) = liveData {
        emit(Result.Loading)
        try {
            val searchedIngredientsResponse = apiService.searchIngredients(query)
            emit(Result.Success(searchedIngredientsResponse))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun searchKeluhanIngredients(query: String) = liveData {
        emit(Result.Loading)
        try {
            val searchedIngredientsResponse = apiService.searchKeluhanIngredients(query)
            emit(Result.Success(searchedIngredientsResponse))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getIngredientsList() = liveData {
        emit(Result.Loading)
        try {
            val ingredientsResponse = apiService.getIngredients()
            emit(Result.Success(ingredientsResponse))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }
}