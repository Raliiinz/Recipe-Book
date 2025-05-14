package com.example.recipebook.search.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.recipebook.domain.model.RecipeModel

class SearchDiffItemCallback : DiffUtil.ItemCallback<RecipeModel>() {
    override fun areItemsTheSame(
        oldItem: RecipeModel,
        newItem: RecipeModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: RecipeModel,
        newItem: RecipeModel
    ): Boolean = oldItem == newItem
}
