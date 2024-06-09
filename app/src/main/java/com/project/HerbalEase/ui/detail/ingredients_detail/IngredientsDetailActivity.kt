package com.project.HerbalEase.ui.detail.ingredients_detail

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.project.HerbalEase.data.model.Ingredients
import com.project.HerbalEase.databinding.ActivityIngredientsDetailBinding
import com.project.HerbalEase.ui.adapter.BenefitAdapter
import com.project.HerbalEase.ui.adapter.KeywordsAdapter

class IngredientsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIngredientsDetailBinding
    private val ingredients by lazy {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_INGREDIENTS)
        } else {
            intent.getParcelableExtra(EXTRA_INGREDIENTS, Ingredients::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngredientsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFullScreenSize()
        setViews()
        setListeners()
    }

    private fun setFullScreenSize() {
        enableEdgeToEdge()
    }

    private fun setViews() {
        binding.apply {
            ingredients?.let {
                Glide.with(root)
                    .load(it.imageUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(ivIngredient)

                tvNameIngredients.text = it.name
                tvDesc.text = it.description

                if (it.listKandungan.isNotEmpty()) {
                    layoutKandugnan.isVisible = true

                    val kandunganAdapter = KeywordsAdapter()
                    kandunganAdapter.submitList(it.listKandungan)

                    rvKandungan.apply {
                        layoutManager =
                            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = kandunganAdapter
                    }
                } else {
                    layoutKandugnan.isVisible = false
                }

                if (it.listKhasiat.isNotEmpty()) {
                    sectionBenefit.isVisible = true

                    val benefitAdapter = BenefitAdapter()
                    benefitAdapter.submitList(it.listKhasiat)

                    rvBenefit.apply {
                        layoutManager =
                            LinearLayoutManager(this@IngredientsDetailActivity)
                        adapter = benefitAdapter
                    }
                } else {
                    sectionBenefit.isVisible = false
                }

                if (it.listKeluhan.isNotEmpty()) {
                    layoutKeluhan.isVisible = true

                    val keluhanAdapter = KeywordsAdapter()
                    keluhanAdapter.submitList(it.listKeluhan)

                    rvKeluhan.apply {
                        layoutManager =
                            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
                        adapter = keluhanAdapter
                    }
                } else {
                    layoutKeluhan.isVisible = true
                }

//                if (it.listKeywords.isNotEmpty()) {
//                    keywordsSeparator.isVisible = true
//                    layoutKeywords.isVisible = true
//
//                    val keywordsAdapter = KeywordsAdapter()
//                    keywordsAdapter.submitList(it.listKeywords)
//
//                    rvKeywords.apply {
//                        layoutManager =
//                            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
//                        adapter = keywordsAdapter
//                    }
//                } else {
//                    keywordsSeparator.isVisible = true
//                    layoutKeywords.isVisible = true
//                }
            } ?: run {
                // Handling ketika gagal memuat Bahan
            }
        }
    }

    private fun setListeners() {
        binding.apply {
            toolbar.setNavigationOnClickListener { finish() }
        }
    }

    companion object {
        const val EXTRA_INGREDIENTS = "extra_ingredients"
    }
}