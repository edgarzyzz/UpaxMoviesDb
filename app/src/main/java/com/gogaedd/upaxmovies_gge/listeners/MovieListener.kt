package com.gogaedd.upaxmovies_gge.listeners

import com.gogaedd.upaxmovies_gge.core.persistence.model.Movie

interface MovieListener {
    fun onClickMovie(moview: Movie)
}