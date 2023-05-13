package com.danvento.coinapi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.danvento.coinapi.data.database.dao.AssetDao
import com.danvento.coinapi.data.database.entities.AssetEntity

@Database(entities = [AssetEntity::class], version = 1)
abstract class AssetDatabase: RoomDatabase() {

    abstract fun assetDao(): AssetDao

}