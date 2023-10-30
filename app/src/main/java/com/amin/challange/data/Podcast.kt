package com.amin.challange.data

import androidx.compose.runtime.Immutable

@Immutable
data class Podcast(
    val id: String,
    val title: String,
    val description :String,
    val imageUrl:String,
    val author:String,
    val isFavourite:Boolean?
)
