package com.dicoding.capstone.watchlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstone.core.domain.usecase.MoveeUseCase

class WatchlistViewModel(moveeUseCase: MoveeUseCase) : ViewModel() {

    val moviesWatchlist = moveeUseCase.getWatchlist().asLiveData()

}