package com.example.newsinshort.di

import android.util.Log
import com.example.newsinshort.BuildConfig
import com.example.newsinshort.data.api.ApiService
import com.example.utilities.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule  {

    @Provides
    fun providesHttpLoggingInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor{ message->
            Log.d("NEWS-API", message)
        }.apply {
            if (BuildConfig.DEBUG){
                level = HttpLoggingInterceptor.Level.BODY
            }else{
                level = HttpLoggingInterceptor.Level.NONE
            }

        }
    }

    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .apply { addInterceptor(loggingInterceptor) }
            .connectTimeout(Constants.CONNECT_TIME_OUT, TimeUnit.SECONDS)
            .callTimeout(Constants.CALL_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIME_OUT, TimeUnit.SECONDS)
            .build()
    }

    //Tambahkan Moshi dan sesuaikan DTO annotation Moshi pada entity/model
    @Provides
    fun provideMoshiBuilder(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient,moshi : Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    fun provideApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }
}