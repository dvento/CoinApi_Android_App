package com.danvento.coinapi.core

import android.content.Context
import androidx.room.Room
import com.danvento.coinapi.data.database.AssetDatabase

object RoomModule {

    private const val ASSET_DATABASE = "asset_database"

    fun provideRoom(context: Context) = Room.databaseBuilder(
        context,
        AssetDatabase::class.java, ASSET_DATABASE
    ).build()

    fun provideAssetDao(database: AssetDatabase) = database.assetDao()

}