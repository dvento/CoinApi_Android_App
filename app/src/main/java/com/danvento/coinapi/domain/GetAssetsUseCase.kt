package com.danvento.coinapi.domain

import com.danvento.coinapi.data.AssetRepository
import com.danvento.coinapi.data.database.entities.toAssetEntity
import com.danvento.coinapi.domain.model.AssetItem

class GetAssetsUseCase(
    private val assetRepository: AssetRepository
) {
    suspend operator fun invoke(): List<AssetItem> {

        // We try to get the response from the api
        val assetsFromApi = assetRepository.getAssetsFromApi()

        /*
        if response is empty, we retrieve the data from Room
        This serves as a fallback mechanism in case the api is down,
        the user is offline, or there's any other error in the call
        */
        return if (assetsFromApi.isNotEmpty()) {
            assetRepository.clearDatabase()
            assetRepository.insertAllAssets(assetsFromApi.map { it.toAssetEntity() })
            assetsFromApi
        } else {
            assetRepository.getAssetsFromRoom()
        }
    }
}