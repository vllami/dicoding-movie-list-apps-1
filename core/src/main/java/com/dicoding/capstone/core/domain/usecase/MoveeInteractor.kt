package com.dicoding.capstone.core.domain.usecase

import com.dicoding.capstone.core.domain.model.Movee
import com.dicoding.capstone.core.domain.repository.IMoveeRepository

class MoveeInteractor(private val iMoveeRepository: IMoveeRepository) : MoveeUseCase {

    override fun getList() = iMoveeRepository.getList()

    override fun getWatchlist() = iMoveeRepository.getWatchlist()

    override fun setWatchlist(movee: Movee, state: Boolean) = iMoveeRepository.setWatchlist(movee, state)

}