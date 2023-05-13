package com.danvento.coinapi.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danvento.coinapi.data.database.entities.AssetEntity

@Dao
interface AssetDao {

    @Query("SELECT * FROM asset_table")
    suspend fun getAllAssets(): List<AssetEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(assets: List<AssetEntity>)

    @Query("DELETE FROM asset_table")
    suspend fun clearDatabase()


}