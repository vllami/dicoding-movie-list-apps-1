package com.dicoding.capstone.ui.details

import androidx.lifecycle.ViewModel
import com.dicoding.capstone.core.domain.model.Movee
import com.dicoding.capstone.core.domain.usecase.MoveeUseCase

class DetailsViewModel(private val moveeUseCase: MoveeUseCase) : ViewModel() {

    fun watchlist(movee: Movee, newState: Boolean) = moveeUseCase.setWatchlist(movee, newState)

}