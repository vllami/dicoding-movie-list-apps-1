package com.dicoding.capstone.core.di

import com.dicoding.capstone.core.Constant.BASE_URL
import com.dicoding.capstone.core.Constant.DATABASE
import com.dicoding.capstone.core.Constant.TIME_OUT
import com.dicoding.capstone.core.domain.repository.IMoveeRepository
import com.dicoding.capstone.core.source.MoveeRepository
import com.dicoding.capstone.core.source.local.LocalDataSource
import com.dicoding.capstone.core.source.local.room.MoveeDatabase
import com.dicoding.capstone.core.source.remote.RemoteDataSource
import com.dicoding.capstone.core.source.remote.network.TMDBService
import com.dicoding.capstone.core.utils.AppExecutors
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit.Builder
import java.util.concurrent.TimeUnit.SECONDS
import androidx.room.Room.databaseBuilder as Room
import okhttp3.OkHttpClient.Builder as OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory.create as GsonConverterFactory

val databaseModule = module {
    factory {
        get<MoveeDatabase>().moveeDAO()
    }
    single {
        Room(androidContext(), MoveeDatabase::class.java, DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    single {
        OkHttpClient()
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .connectTimeout(TIME_OUT.toLong(), SECONDS)
            .readTimeout(TIME_OUT.toLong(), SECONDS)
            .build()
    }
    single {
        val retrofit = Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory())
            .client(get())
            .build()

        retrofit.create(TMDBService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single {
        RemoteDataSource(get())
    }
    factory {
        AppExecutors()
    }
    single<IMoveeRepository> {
        MoveeRepository(
            get(),
            get(),
            get()
        )
    }
}