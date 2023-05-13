package com.danvento.coinapi.data.network

import android.util.Log
import com.danvento.coinapi.data.model.ApiResponse
import com.danvento.coinapi.data.model.Asset
import com.danvento.coinapi.data.model.AssetIcon
import com.danvento.coinapi.data.model.ExchangeRate

class ApiService(
    private val coinApi: CoinApiClient
) {

    suspend fun getAssets(): ApiResponse<List<Asset>> {



        // TODO hey assets mock
//        return ApiResponse.Success(data = mockedAssetList)

        return try {
            val response = coinApi.getAssets()
            Log.i("hey ApiService", " code: ${response.code()}. Header: ${response.raw().request.headers} URL: ${response.raw().request.url}")


            handleResponse(response)
        } catch (e: Exception) {
            ApiResponse.Error(message = e.message ?: "Unknown error")
        }
    }

    suspend fun getAssetIcons(iconSize: Int): ApiResponse<List<AssetIcon>> {
        return try {
            val response = coinApi.getAssetIcons(iconSize)
            handleResponse(response)
        } catch (e: Exception) {
            ApiResponse.Error(message = e.message ?: "Unknown error")
        }
    }

    suspend fun getAssetById(assetId: String): ApiResponse<Asset> {
        return try {
            val response = coinApi.getAssetById(assetId)
            handleResponse(response)
        } catch (e: Exception) {
            ApiResponse.Error(message = e.message ?: "Unknown error")
        }
    }

    suspend fun getExchangeRate(
        assetIdBase: String,
        assetIdQuote: String
    ): ApiResponse<ExchangeRate> {
        return try {
            val response = coinApi.getExchangeRate(assetIdBase, assetIdQuote)

            handleResponse(response)
        } catch (e: Exception) {
            ApiResponse.Error(message = e.message ?: "Unknown error")
        }
    }

    private fun <T : Any> handleResponse(
        response: retrofit2.Response<T>
    ): ApiResponse<T> {
        return when (response.code()) {
            200 -> ApiResponse.Success(data = response.body()!!)
            401 -> ApiResponse.Error(message = "Unauthorized",null, response.code())
            else -> ApiResponse.Error(message = "URL: ${response.raw().request.url}", null, response.code())
        }
    }



    val mockedAssetList = listOf(
        Asset(
            assetId = "BTC",
            dataEnd = "2023-05-06",
            dataOrderbookEnd = "2023-05-06",
            dataOrderbookStart = "2023-05-06",
            dataQuoteEnd = "2023-05-06",
            dataQuoteStart = "2023-05-06",
            dataStart = "2023-05-06",
            dataSymbolsCount = 1,
            dataTradeEnd = "2023-05-06",
            dataTradeStart = "2023-05-06",
            idIcon = "https://www.example.com/btc.png",
            name = "Bitcoin",
            priceUsd = 57938.12,
            typeIsCrypto = 1,
            volume1dayUsd = 52498847867.34,
            volume1hrsUsd = 2187452937.68,
            volume1mthUsd = 1384932877650.78
        ),
        Asset(
            assetId = "ETH",
            dataEnd = "2023-05-06",
            dataOrderbookEnd = "2023-05-06",
            dataOrderbookStart = "2023-05-06",
            dataQuoteEnd = "2023-05-06",
            dataQuoteStart = "2023-05-06",
            dataStart = "2023-05-06",
            dataSymbolsCount = 1,
            dataTradeEnd = "2023-05-06",
            dataTradeStart = "2023-05-06",
            idIcon = "https://www.example.com/eth.png",
            name = "Ethereum",
            priceUsd = 3797.89,
            typeIsCrypto = 1,
            volume1dayUsd = 26793824514.56,
            volume1hrsUsd = 1134859376.23,
            volume1mthUsd = 780293456743.98
        ),
        Asset(
            assetId = "USD",
            dataEnd = "2023-05-06",
            dataOrderbookEnd = "2023-05-06",
            dataOrderbookStart = "2023-05-06",
            dataQuoteEnd = "2023-05-06",
            dataQuoteStart = "2023-05-06",
            dataStart = "2023-05-06",
            dataSymbolsCount = 1,
            dataTradeEnd = "2023-05-06",
            dataTradeStart = "2023-05-06",
            idIcon = "https://www.example.com/usd.png",
            name = "United States Dollar",
            priceUsd = 1.0,
            typeIsCrypto = 0,
            volume1dayUsd = 135793582956.34,
            volume1hrsUsd = 5384756723.78,
            volume1mthUsd = 3650987654310.67
        )
    )


}

