package com.danvento.coinapi.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.danvento.coinapi.domain.model.AssetItem

@Entity(tableName = "asset_table", primaryKeys = ["asset_id"])
data class AssetEntity(
    @ColumnInfo(name = "asset_id") val assetId: String,
    @ColumnInfo(name = "icon_url") val iconUrl: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price_eur") val priceEur: Double,
    @ColumnInfo(name = "price_usd") val priceUsd: Double,
    @ColumnInfo(name = "volume_1day_usd") val volume1dayUsd: Double,
    @ColumnInfo(name = "volume_1hrs_usd") val volume1hrsUsd: Double,
    @ColumnInfo(name = "volume_1mth_usd") val volume1mthUsd: Double,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean,
    @ColumnInfo(name = "timestamp") var timestamp: Long
)

fun AssetItem.toAssetEntity() = AssetEntity(
    assetId = assetId,
    iconUrl = iconUrl,
    name = name,
    priceEur = priceEur,
    priceUsd = priceUsd,
    volume1dayUsd = volume1dayUsd,
    volume1hrsUsd = volume1hrsUsd,
    volume1mthUsd = volume1mthUsd,
    isFavorite = isFavorite,
    timestamp = System.currentTimeMillis()
)