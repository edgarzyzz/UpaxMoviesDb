package com.gogaedd.upaxmovies_gge.core.persistence.net

import com.gogaedd.upaxmovies_gge.core.ConstantsApp
import com.gogaedd.upaxmovies_gge.core.persistence.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("${ConstantsApp.WS_END_POINT}popular")
    fun getPopularMovies(@Query("api_key")apiKey:String): Call<MoviesResponse>





}