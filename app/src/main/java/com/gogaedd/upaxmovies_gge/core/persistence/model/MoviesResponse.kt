package com.gogaedd.upaxmovies_gge.core.persistence.model

import com.gogaedd.upaxmovies_gge.core.persistence.model.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    var page: Int=0,

    @SerializedName("results")
    var listMovies: MutableList<Movie> = mutableListOf()

)
