package com.example.lokal_task.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.lokal_task.R
import com.example.lokal_task.domain.model.Product
import com.example.lokal_task.presentation.Dimens.ExtraSmallPadding
import com.example.lokal_task.presentation.Dimens.ExtraSmallPadding2
import com.example.lokal_task.presentation.Dimens.MediumPadding1
import com.example.lokal_task.presentation.common.ArticlesList

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Product>,
    navigateTodetails: (Product) -> Unit
){
    val titles by remember {
//        It's a derived state that is recomposed whenever the underlying data changes.
//        This block of code calculates the titles string by joining the titles of the first
//        10 articles with a "\uD83d\uDFE5" separator. If there are fewer than 10 articles, an empty string is assigned.
        derivedStateOf {
            if(articles.itemCount > 10){
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5"){it.title}
            }else{
                ""
            }
        }
    }

    // lets actually built the screen
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = MediumPadding1)
        .statusBarsPadding()
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)){
            Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(50.dp)
                    .clip(MaterialTheme.shapes.small)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Lokal App",
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.Lokal),
                fontSize = 19.sp
            )
        }

        // to add the text animation we use basic marquee
        Text(text = titles, modifier = Modifier
            .fillMaxWidth()
            .padding(start = MediumPadding1)
            .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = {
                navigateTodetails(it)
            }
        )

    }
}