package com.dicoding.capstone.core.utils

import com.dicoding.capstone.core.domain.model.Movee
import com.dicoding.capstone.core.source.local.entity.MoveeEntity
import com.dicoding.capstone.core.source.remote.response.Response

object DataMapper {

    fun mapResponseToEntities(input: List<Response>): List<MoveeEntity> {
        val moveeEntity = ArrayList<MoveeEntity>()

        input.map { response ->
            response.apply {
                MoveeEntity(
                    id = id,
                    backdrop = backdrop,
                    poster = poster,
                    title = title,
                    releaseDate = releaseDate,
                    rating = rating,
                    synopsis = synopsis,
                    watchlist = false
                ).also {
                    moveeEntity.add(it)
                }
            }
        }

        return moveeEntity
    }

    fun mapEntitiesToDomain(input: List<MoveeEntity>): List<Movee> {
        return input.map {
            Movee(
                id = it.id,
                backdrop = it.backdrop,
                poster = it.poster,
                title = it.title,
                releaseDate = it.releaseDate,
                rating = it.rating,
                synopsis = it.synopsis,
                watchlist = it.watchlist
            )
        }
    }

    fun mapDomainToEntity(input: Movee) = MoveeEntity(
        id = input.id,
        backdrop = input.backdrop,
        poster = input.poster,
        title = input.title,
        releaseDate = input.releaseDate,
        rating = input.rating,
        synopsis = input.synopsis,
        watchlist = input.watchlist
    )

}