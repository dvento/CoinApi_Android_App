package com.danvento.coinapi.core

import com.danvento.coinapi.BuildConfig
import com.danvento.coinapi.data.network.CoinApiClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    private const val BASE_URL = "https://rest.coinapi.io/v1/"
    private const val BASE_URL_SANDBOX = "https://rest-sandbox.coinapi.io/v1/"
    // Place your API KEY in local.properties file, named as "API_KEY"
    private const val API_KEY = BuildConfig.API_KEY

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("X-CoinAPI-Key", API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideApiCoinClient(retrofit: Retrofit) = retrofit.create(CoinApiClient::class.java)

}