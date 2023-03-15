package com.example.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage

@Composable
internal fun LoadImage(
    modifier: Modifier = Modifier,
    url: String,
    @DrawableRes placeholderId: Int? = null,
    description: String = "",
    contentScale: ContentScale = ContentScale.Crop
) {

    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = description,
        fallback = placeholderId?.let { id -> painterResource(id = id) },
        error = placeholderId?.let { id -> painterResource(id = id) },
        contentScale = contentScale
    )

}