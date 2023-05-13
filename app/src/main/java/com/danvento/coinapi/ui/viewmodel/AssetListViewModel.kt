package com.danvento.coinapi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.danvento.coinapi.domain.GetAssetsUseCase
import com.danvento.coinapi.domain.model.AssetItem
import kotlinx.coroutines.launch

class AssetListViewModel(
    private val getAssetsUseCase: GetAssetsUseCase
) : ViewModel() {

    private val assetsModel = MutableLiveData<List<AssetItem>>()
    val assetData= assetsModel.asFlow()

    fun getAssets() {

        viewModelScope.launch {
            val assets = getAssetsUseCase()

            assetsModel.postValue(assets)
        }

    }
}