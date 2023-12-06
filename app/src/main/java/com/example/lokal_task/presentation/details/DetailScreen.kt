package com.example.lokal_task.presentation.details


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lokal_task.R
import com.example.lokal_task.domain.model.Product
import com.example.lokal_task.presentation.Dimens.ArticleImageHeight
import com.example.lokal_task.presentation.Dimens.ExtraSmallPadding
import com.example.lokal_task.presentation.Dimens.ExtraSmallPadding2
import com.example.lokal_task.presentation.Dimens.MediumPadding1
import com.example.lokal_task.presentation.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    product : Product,
    event : (DetailsEvent) -> Unit,
    navigateUp : () -> Unit
) {
    val context = LocalContext.current

    val imageList : List<String> = product.images

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBackClick = navigateUp)

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = MediumPadding1,
                end = MediumPadding1,
                top = MediumPadding1
            )
        ){
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(product.thumbnail).build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(ArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(id = R.color.text_title)
                )

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.height(MediumPadding1))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.Lokal), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.size(
                            ArticleImageHeight
                        )
                    ) {
                        items(imageList) { imageUrl ->
                            LazyRowItem(imageUrl)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(ExtraSmallPadding))

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(id = R.color.Lokal), RoundedCornerShape(16.dp))
                        .padding(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "PRICE :",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.text_title)
                                )
                            )

                            Text(
                                text = "$" + product.price.toString(),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.text_title)
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(ExtraSmallPadding))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Discount Percentage :",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.text_title)
                                )
                            )

                            Text(
                                text = product.discountPercentage.toString() + "%",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.text_title)
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(ExtraSmallPadding))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Rating :",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.text_title)
                                )
                            )

                            Text(
                                text = product.rating.toString(),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = colorResource(id = R.color.text_title)
                                )
                            )
                        }

                    }


                }
                Spacer(modifier = Modifier.height(MediumPadding1))
            }
            }
        }
    }


@Composable
fun LazyRowItem(imageResource: String) {
    val context = LocalContext.current
    AsyncImage(model = ImageRequest.Builder(context = context).data(imageResource).build(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(ArticleImageHeight)
            .clip(MaterialTheme.shapes.medium),
        contentScale = ContentScale.Crop
    )
}
