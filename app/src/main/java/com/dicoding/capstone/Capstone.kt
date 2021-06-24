package com.dicoding.capstone

import android.app.Application
import com.dicoding.capstone.core.di.databaseModule
import com.dicoding.capstone.core.di.networkModule
import com.dicoding.capstone.core.di.repositoryModule
import com.dicoding.capstone.di.useCaseModule
import com.dicoding.capstone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Capstone : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@Capstone)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

}