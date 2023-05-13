package com.danvento.coinapi.data.model

import com.google.gson.annotations.SerializedName


data class Asset(
    @SerializedName("asset_id")
    val assetId: String? = null,
    @SerializedName("data_end")
    val dataEnd: String? = null,
    @SerializedName("data_orderbook_end")
    val dataOrderbookEnd: String? = null,
    @SerializedName("data_orderbook_start")
    val dataOrderbookStart: String? = null,
    @SerializedName("data_quote_end")
    val dataQuoteEnd: String? = null,
    @SerializedName("data_quote_start")
    val dataQuoteStart: String? = null,
    @SerializedName("data_start")
    val dataStart: String? = null,
    @SerializedName("data_symbols_count")
    val dataSymbolsCount: Int? = null,
    @SerializedName("data_trade_end")
    val dataTradeEnd: String? = null,
    @SerializedName("data_trade_start")
    val dataTradeStart: String? = null,
    @SerializedName("id_icon")
    var idIcon: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price_usd")
    val priceUsd: Double? = null,
    @SerializedName("type_is_crypto")
    val typeIsCrypto: Int? = null,
    @SerializedName("volume_1day_usd")
    val volume1dayUsd: Double? = null,
    @SerializedName("volume_1hrs_usd")
    val volume1hrsUsd: Double? = null,
    @SerializedName("volume_1mth_usd")
    val volume1mthUsd: Double? = null
)

data class AssetIcon(
    @SerializedName("asset_id")
    val assetId: String? = null,
    @SerializedName("url")
    val url: String? = null
)
data class ExchangeRate(
    @SerializedName("asset_id_base")
    val assetIdBase: String? = null,
    @SerializedName("asset_id_quote")
    val assetIdQuote: String? = null,
    @SerializedName("rate")
    val rate: Double? = null,
    @SerializedName("time")
    val time: String? = null
)

