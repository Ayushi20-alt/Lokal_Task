package com.example.lokal_task.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.lokal_task.R
import com.example.lokal_task.domain.model.Product
import com.example.lokal_task.presentation.Dimens.ArticleCardSize
import com.example.lokal_task.presentation.Dimens.ExtraSmallPadding
import com.example.lokal_task.presentation.Dimens.ExtraSmallPadding2
import com.example.lokal_task.ui.theme.Lokal_taskTheme

@Composable
fun ArticleCard(
    modifier : Modifier = Modifier,
    product: Product,
    onCLick : ()->Unit
){
    val context = LocalContext.current
    Row(
        modifier = modifier.clickable { onCLick() }
    ) {
        // we using coil to load image
        AsyncImage(modifier = Modifier
            .size(ArticleCardSize)
            .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context).data(product.thumbnail).build(),
            contentDescription = null)
        
        Spacer(modifier = Modifier.padding(ExtraSmallPadding))
        
        Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
            .padding(horizontal = ExtraSmallPadding)
            .height(
                ArticleCardSize
            )) {
            Text(text = product.title, style = MaterialTheme.typography.bodyMedium, color = colorResource(
                id = R.color.text_title
            ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically){
                Text(
                    text = "$" + product.price.toString(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = modifier.width(ExtraSmallPadding2))
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body),
                    fontSize = 9.sp
                )
            }
        }
    }
    
}

@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview(){
    Lokal_taskTheme {
        ArticleCard(
            product = Product(
                brand = "Apple",
                category = "",
                description = "Hi there this is iphone",
                discountPercentage = 0.0,
                id = 1,
                images = listOf(),
                price = 1,
                rating = 0.0,
                stock = 2,
                thumbnail = "",
                title = "iPone"
            )
        ) {

        }
    }
}