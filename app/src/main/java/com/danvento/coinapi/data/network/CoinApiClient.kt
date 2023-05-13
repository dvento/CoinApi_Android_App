package com.danvento.coinapi.data.network

import com.danvento.coinapi.data.ApiConstants
import com.danvento.coinapi.data.model.Asset
import com.danvento.coinapi.data.model.AssetIcon
import com.danvento.coinapi.data.model.ExchangeRate
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApiClient {

    @GET(ApiConstants.PATH_ASSETS)
    suspend fun getAssets(): Response<List<Asset>>

    @GET(ApiConstants.PATH_ASSETS_ICONS)
    suspend fun getAssetIcons(@Path("iconSize") iconSize: Int): Response<List<AssetIcon>>

    @GET(ApiConstants.PATH_ASSETS_ID)
    suspend fun getAssetById(@Path("assetId") assetId: String): Response<Asset>

    @GET(ApiConstants.PATH_EXCHANGE_RATE)
    suspend fun getExchangeRate(
        @Path("assetIdBase") assetIdBase: String,
        @Path("assetIdQuote") assetIdQuote: String
    ): Response<ExchangeRate>

}