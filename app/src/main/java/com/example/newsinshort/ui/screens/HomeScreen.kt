package com.example.newsinshort.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsinshort.data.entity.NewsResponse
import com.example.newsinshort.ui.components.Loader
import com.example.newsinshort.ui.viewmodel.NewsViewModel
import com.example.utilities.ResourceState

private const val TAG = "HomeScreen"
@Composable
fun HomeScreen(
    newsViewModel: NewsViewModel = hiltViewModel()
){
    val newsResponse by newsViewModel.news.collectAsState()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when(newsResponse){
            is ResourceState.Loading -> {
                Log.d(TAG, "Loading...")
                Loader()
            }
            is ResourceState.Success -> {
                Log.d(TAG, "Fetch data Success...")
            }
            is ResourceState.Error -> {
                Log.d(TAG, "Error Fetching Data ${(newsResponse as ResourceState.Error<NewsResponse>).errorMessage}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}