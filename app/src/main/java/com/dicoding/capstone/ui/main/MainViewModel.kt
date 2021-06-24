package com.dicoding.capstone.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capstone.core.domain.usecase.MoveeUseCase

class MainViewModel(moveeUseCase: MoveeUseCase) : ViewModel() {

    val movee = moveeUseCase.getList().asLiveData()

}