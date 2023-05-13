package com.danvento.coinapi.di

import com.danvento.coinapi.core.RetrofitModule
import com.danvento.coinapi.core.RoomModule
import com.danvento.coinapi.data.AssetRepository
import com.danvento.coinapi.data.network.ApiService
import com.danvento.coinapi.domain.GetAssetsUseCase
import com.danvento.coinapi.ui.viewmodel.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModule = module {
    single { RetrofitModule.getRetrofit() }
    single { RetrofitModule.provideApiCoinClient(get()) }
    single { RoomModule.provideRoom(androidContext()) }
    single { RoomModule.provideAssetDao(get()) }
    single { ApiService(get()) }
    single { AssetRepository(get(), get()) }
}

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }
}

val useCaseModule = module {
    single { GetAssetsUseCase(get()) }
}
