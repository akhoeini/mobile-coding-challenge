package com.amin.challange.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import androidx.constraintlayout.compose.Dimension.Companion.preferredWrapContent
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.amin.challange.R
import com.amin.challange.data.Podcast


@Composable
fun PodcastList(
    navigator: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val viewModel: PodcastListViewModel = PodcastListViewModel()
    val viewState by viewModel.state.collectAsState()
    var podcasts = viewState.topPodcasts

    LazyColumn(
        verticalArrangement = Arrangement.Center
    ) {
        items(podcasts) { podcast: Podcast ->
                PodcastListItem(podcast, Modifier.fillParentMaxWidth(), navigator)
        }
    }
}

@Composable
fun PodcastListItem(
    podcast: Podcast,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    ConstraintLayout(modifier = modifier.clickable { onClick(podcast.id) }) {
        val (
            title, author, image, favorite, divider
        ) = createRefs()


        // If we have an image Url, we can show it using Coil
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(podcast.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(MaterialTheme.shapes.medium)
                .constrainAs(image) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 16.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
        )

        Text(
            text = podcast.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(title) {
                linkTo(
                    start = image.end,
                    end = parent.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp,
                    bias = 0f
                )
                top.linkTo(parent.top, 16.dp)
                height = preferredWrapContent
                width = preferredWrapContent
            }
        )

        Text(
            text = podcast.author,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            modifier = Modifier.constrainAs(author) {
                linkTo(
                    start = image.end,
                    end = parent.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp,
                    bias = 0f
                )
                top.linkTo(title.bottom, 4.dp)
                height = preferredWrapContent
                width = preferredWrapContent
            }
        )
        if (podcast.isFavourite == true) {
            Text(
                text = stringResource(R.string.favoured),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Red,
                modifier = Modifier.constrainAs(favorite) {
                    linkTo(
                        start = image.end,
                        end = parent.end,
                        startMargin = 16.dp,
                        endMargin = 16.dp,
                        bias = 0f
                    )
                    top.linkTo(author.bottom, 4.dp)
                    height = preferredWrapContent
                    width = preferredWrapContent
                }
            )
        }
        Divider(Modifier.constrainAs(divider) {
            linkTo(
                start = parent.end,
                end = parent.end,
                top = image.bottom,
                bottom = parent.bottom,
                startMargin = 16.dp,
                endMargin = 16.dp
            )
            centerHorizontallyTo(parent)
            width = fillToConstraints

        })
    }
}

@Preview
@Composable
fun preview() {
    PodcastListItem(Podcast(
        "id: String",
        "title: String",
        "val description :String?",
        "val imageUrl:String?",
        "val author:String?",
        true
    ), Modifier.background(Color.White), {})
}

