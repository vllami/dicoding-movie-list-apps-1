package com.dicoding.capstone.watchlist.di

import com.dicoding.capstone.watchlist.ui.WatchlistViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val watchlistModule = module {
    viewModel {
        WatchlistViewModel(get())
    }
}