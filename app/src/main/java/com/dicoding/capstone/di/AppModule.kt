package com.dicoding.capstone.di

import com.dicoding.capstone.core.domain.usecase.MoveeInteractor
import com.dicoding.capstone.core.domain.usecase.MoveeUseCase
import com.dicoding.capstone.ui.details.DetailsViewModel
import com.dicoding.capstone.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoveeUseCase> {
        MoveeInteractor(get())
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        DetailsViewModel(get())
    }
}