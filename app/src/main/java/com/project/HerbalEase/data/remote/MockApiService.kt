package com.project.HerbalEase.data.remote

import com.project.HerbalEase.utils.FakeData

object MockApiService {
    fun getHeadlineIngredients() = FakeData.generateHeadlineIngredients()

    fun getIngredients() = FakeData.generateIngredients()

    fun searchIngredients(query: String) = FakeData.searchIngredients(query)

    fun searchKeluhanIngredients(query: String) = FakeData.searchKeluhanIngredients(query)
}