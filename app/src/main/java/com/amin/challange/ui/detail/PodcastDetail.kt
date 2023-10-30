package com.amin.challange.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.amin.challange.data.Podcast


@Composable
fun PodcastDetail(modifier: Modifier = Modifier, podcast: Podcast) {
    val viewModel = PodcastDetailViewModel(podcast)
    val viewState by viewModel.state.collectAsState()
    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        val (
            backBtn, title, author, image, favoriteBtn, description
        ) = createRefs()

        Button(
            onClick = { /*TODO*/ },
            Modifier
                .padding(4.dp)
                .constrainAs(backBtn) {
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                    top.linkTo(parent.top, 24.dp)
                    start.linkTo(parent.start, 24.dp)
                }, colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "ArrowBack",
                tint = Color.Black,
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.Transparent)
            )
            Text(text = "Back", color = Color.Black)

        }

        Text(
            text = podcast.title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.constrainAs(title) {
                height = Dimension.preferredWrapContent
                width = Dimension.preferredWrapContent
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp,
                )
                top.linkTo(backBtn.bottom, 24.dp)

            }
        )
        Text(
            text = podcast.author,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            modifier = Modifier.constrainAs(author) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp,
                )
                top.linkTo(title.bottom, 4.dp)
                height = Dimension.preferredWrapContent
                width = Dimension.preferredWrapContent
            }
        )

        // If we have an image Url, we can show it using Coil
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(podcast.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(MaterialTheme.shapes.medium)
                .aspectRatio(1f)
                .constrainAs(image) {
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                    start.linkTo(parent.start, 48.dp)
                    end.linkTo(parent.end, 48.dp)
                    top.linkTo(author.bottom, 24.dp)
                },
        )

        TextButton(
            onClick = { /*TODO*/ },
            Modifier
                .constrainAs(favoriteBtn) {
                    height = Dimension.preferredWrapContent
                    width = Dimension.preferredWrapContent
                    top.linkTo(image.bottom, 24.dp)
                    start.linkTo(parent.start, 24.dp)
                    end.linkTo(parent.end, 24.dp)

                },
            colors = ButtonDefaults.buttonColors(Color.Red),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("favorite", color = Color.White)
        }
//Todo make this page scrollable for long discription podcast
        Text(
            text = podcast.description,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Red,
            modifier = Modifier.constrainAs(description) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    startMargin = 16.dp,
                    endMargin = 16.dp,
                )
                top.linkTo(favoriteBtn.bottom, 24.dp)
                bottom.linkTo(parent.bottom)
                height = Dimension.preferredWrapContent
                width = Dimension.preferredWrapContent
            },
            textAlign = TextAlign.Center
        )

    }
}

@Preview
@Composable
fun preview() {
    PodcastDetail(
        Modifier.background(Color.White), Podcast(
            "id: String",
            "title: String",
            "val description :String?",
            "val imageUrl:String?",
            "val author:String?",
            true
        )
    )
}