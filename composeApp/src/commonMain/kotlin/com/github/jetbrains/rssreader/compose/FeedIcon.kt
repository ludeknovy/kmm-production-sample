package com.github.jetbrains.rssreader.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.github.jetbrains.rssreader.core.entity.Feed
import com.github.jetbrains.rssreader.images.MRImages
import com.github.jetbrains.rssreader.strings.MRStrings
import com.seiko.imageloader.rememberAsyncImagePainter
import io.github.skeptick.libres.compose.painterResource

@Composable
internal fun FeedIcon(
    feed: Feed?,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    val txtAll = MRStrings.all
    val shortName = remember(feed) { feed?.shortName() ?: txtAll }
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(
                color = if (isSelected) MaterialTheme.colors.secondary else Color.Transparent
            )
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .align(Alignment.Center)
                .background(color = MaterialTheme.colors.primary)
                .clickable(enabled = onClick != null, onClick = onClick ?: {})
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.onPrimary,
                text = shortName
            )
            feed?.imageUrl?.let { url ->
                Image(
                    painter = rememberAsyncImagePainter(url),
                    modifier = Modifier.fillMaxSize(),
                    contentDescription = null
                )
            }
        }
    }
}

private fun Feed.shortName(): String =
    title
        .replace(" ", "")
        .take(2)
        .uppercase()

@Composable
internal fun EditIcon(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colors.secondary)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(MRImages.ic_edit),
            modifier = Modifier.align(Alignment.Center),
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onPrimary),
            contentDescription = null
        )
    }
}