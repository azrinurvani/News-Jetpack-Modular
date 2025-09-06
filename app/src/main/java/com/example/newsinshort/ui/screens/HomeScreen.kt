package com.example.newsinshort.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsinshort.data.entity.NewsResponse
import com.example.newsinshort.ui.components.EmptyStateComponent
import com.example.newsinshort.ui.components.ErrorState
import com.example.newsinshort.ui.components.Loader
import com.example.newsinshort.ui.components.NewsList
import com.example.newsinshort.ui.components.NewsRowComponent
import com.example.newsinshort.ui.viewmodel.NewsViewModel
import com.example.utilities.ResourceState

private const val TAG = "HomeScreen"
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
){
    val newsResponse by newsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){
        //item counts
        100 //example size
    }

    VerticalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxSize(),
        pageSize = PageSize.Fill,
        pageSpacing = 8.dp
    ) { page->
        when(newsResponse){
            is ResourceState.Loading -> {
                Log.d(TAG, "Loading...")
                Loader()
            }
            is ResourceState.Success -> {
                var response = (newsResponse as ResourceState.Success<NewsResponse>).data
                response.articles
                if (response.articles?.isNotEmpty() == true){
                    NewsRowComponent(page, response.articles[page])
                }else{
                    EmptyStateComponent()
                }
            }
            is ResourceState.Error -> {
                ErrorState(message = (newsResponse as ResourceState.Error<NewsResponse>).errorMessage)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}