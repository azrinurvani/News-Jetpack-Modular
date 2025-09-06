package com.example.newsinshort.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.newsinshort.R
import com.example.newsinshort.data.entity.Article
import com.example.newsinshort.data.entity.NewsResponse
import com.example.newsinshort.ui.theme.Purple40

@Composable
fun Loader(){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Purple40
        )
    }

}

@Composable
fun NewsList(response : NewsResponse){
    LazyColumn {
        if (response.articles != null){
            items(response.articles){ article->
                NormalTextComponent(article?.title ?: "NA")
            }
        }
    }
}

@Composable
fun NormalTextComponent(value : String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = FontFamily.Monospace,
            color = Purple40
        )
    )
}

@Composable
fun HeadingTextComponent(value : String, centerAligned : Boolean = false) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        ),
        textAlign = if (centerAligned) TextAlign.Center else TextAlign.Start
    )
}

@Composable
fun NewsRowComponent(page : Int, article : Article?){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            model = article?.urlToImage,
            contentDescription = "content_image",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_placeholder),
            error = painterResource(id = R.drawable.ic_error_image)
        )

        Spacer(modifier = Modifier.height(20.dp))

        HeadingTextComponent(value = article?.title ?: "NA")

        Spacer(modifier = Modifier.height(10.dp))

        NormalTextComponent(value = article?.description ?: "NA")

        Spacer(modifier = Modifier.weight(1f))
        
        AuthorDetailsComponent(
            authorName = article?.author ?: "NA",
            sourceName = article?.source?.name ?: "NA"
        )
    }
}

@Preview
@Composable
fun NewsRowComponentPreview(){
    val article = Article(
        author = "Azri N",
        title = "This is a news title"
    )
    NewsRowComponent(0, article)
}

@Composable
fun FooterTextComponent(value : String){
    Text(
        text = value,
        style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Light
        ),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun AuthorDetailsComponent(authorName : String?, sourceName : String?){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 24.dp),
    ) {
        authorName?.let {
           FooterTextComponent(it)
        }

        Spacer(modifier = Modifier.weight(1f))

        sourceName?.let {
            FooterTextComponent(it)
        }
    }
}

@Composable
fun EmptyStateComponent(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_no_data),
            contentDescription = null
        )

        HeadingTextComponent(
            value = stringResource(R.string.empty_state_message),
            centerAligned = true
        )
    }
}

@Composable
fun ErrorState(message : String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_error_state),
            contentDescription = null
        )

        HeadingTextComponent(
            value = message,
            centerAligned = true
        )
    }
}
