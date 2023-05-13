package com.danvento.coinapi

import android.app.Application
import com.danvento.coinapi.di.applicationModule
import com.danvento.coinapi.di.useCaseModule
import com.danvento.coinapi.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class CoinApiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@CoinApiApp)
            modules(
                listOf(
                    applicationModule,
                    viewModelModule,
                    useCaseModule
                )
            )
        }
    }
}