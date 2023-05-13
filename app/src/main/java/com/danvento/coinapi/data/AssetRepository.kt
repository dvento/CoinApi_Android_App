package com.danvento.coinapi.data

import android.util.Log
import com.danvento.coinapi.data.database.dao.AssetDao
import com.danvento.coinapi.data.database.entities.AssetEntity
import com.danvento.coinapi.data.model.ApiResponse
import com.danvento.coinapi.data.network.ApiService
import com.danvento.coinapi.domain.model.AssetItem
import com.danvento.coinapi.domain.model.toAssetItem

class AssetRepository(
    private val apiService: ApiService,
    private val assetDao: AssetDao
) {

    companion object {
        private val TAG = Companion::class.java.canonicalName
    }

    private var eurRate = 0.0

    suspend fun getAssetsFromApi(): List<AssetItem> {
        val result = apiService.getAssets()
//        val iconsResult = apiService.getAssetIcons(64)

        return if (result is ApiResponse.Error) {
            Log.e(TAG, result.message!!)
            emptyList()
        } else {
            /*
            There are some assets whose "id" is not the same their icon's "assetId",
            So, a more accurate but less reliable way to add the iconUrl to the asset
            is casting the "id_icon" to the default Icon url
             */
            // The following implementation is less accurate but more reliable
            /*result.data!!.forEach { asset ->
                asset.idIcon = iconsResult.data!!.find { it.assetId == asset.assetId }?.url
            }*/

            // TODO: add explanation on the use of eurRate
            // get eur price based on asset list
            eurRate = result.data!!.find { it.assetId == "EUR" }?.priceUsd ?: -1.0

            result.data.map { it.toAssetItem(/*getEurPrice(it.assetId!!, it.priceUsd)*/ it.priceUsd?.div(
                eurRate
            )) }

        }
    }

    private suspend fun getEurPrice(assetId: String, assetPrice: Double?): Double? {
        // We don't need to check for the exchange rate if price is not available
        if (assetPrice == null) return null

        val result = apiService.getExchangeRate(assetId, "EUR")
        return if (result is ApiResponse.Error) {
            // If there are too many requests, we use the less accurate exchange rate
            // We should inform the user that the price is doesn't have the best accuracy

            assetPrice / eurRate

        } else {
            result.data?.rate ?: 0.0
        }
    }

    suspend fun getAssetsFromRoom(): List<AssetItem> {
        return assetDao.getAllAssets().map { it.toAssetItem() }
    }

    suspend fun insertAllAssets(assetsFromApi: List<AssetEntity>) {
        assetDao.insertAll(assetsFromApi)
    }

    suspend fun clearDatabase() = assetDao.clearDatabase()

}