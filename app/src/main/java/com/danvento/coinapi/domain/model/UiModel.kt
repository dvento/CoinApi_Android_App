package com.danvento.coinapi.domain.model

import com.danvento.coinapi.data.ApiConstants
import com.danvento.coinapi.data.database.entities.AssetEntity
import com.danvento.coinapi.data.model.Asset

data class AssetItem(
    val assetId: String = "",
    val iconUrl: String = "",
    val name: String = "",
    val priceEur: Double = 0.0,
    val priceUsd: Double = 0.0,
    val volume1dayUsd: Double = 0.0,
    val volume1hrsUsd: Double = 0.0,
    val volume1mthUsd: Double = 0.0,
    val isFavorite: Boolean = false
)

fun Asset.toAssetItem(priceEur: Double?): AssetItem {
    val cleanedIconId = idIcon?.replace("-", "") ?: ""

    val iconUrl = ApiConstants.ASSET_ICON_URL.format(cleanedIconId)

    return AssetItem(
        assetId = assetId ?: "",
        name = name ?: "",
        iconUrl = iconUrl,
        priceEur = priceEur ?: 0.0,
        priceUsd = priceUsd ?: 0.0,
        volume1dayUsd = volume1dayUsd ?: 0.0,
        volume1hrsUsd = volume1hrsUsd ?: 0.0,
        volume1mthUsd = volume1mthUsd ?: 0.0
    )
}

fun AssetEntity.toAssetItem() = AssetItem(
    assetId = assetId,
    iconUrl = iconUrl,
    name = name,
    priceEur = priceEur,
    priceUsd = priceUsd,
    volume1dayUsd = volume1dayUsd,
    volume1hrsUsd = volume1hrsUsd,
    volume1mthUsd = volume1mthUsd,
    isFavorite = isFavorite
)