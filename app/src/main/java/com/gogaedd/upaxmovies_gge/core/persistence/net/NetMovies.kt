package com.gogaedd.upaxmovies_gge.core.persistence.net

import com.gogaedd.upaxmovies_gge.core.ConstantsApp
import com.gogaedd.upaxmovies_gge.core.persistence.model.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetMovies (): NetBaseHelper(){

    fun sendRequestMostPopular(){
        val call =  apiService.getPopularMovies(ConstantsApp.WS_API_KEY)
        call.enqueue(object : Callback<MoviesResponse>{
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        val listMovies = it.listMovies
                        mListener?.onResultOk(listMovies, ConstantsApp.NET.WS_MOST_POPULAR_GET)
                        return
                    }


                }

                mListener?.onResultError("response error", ConstantsApp.NET.WS_MOST_POPULAR_GET)

            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                mListener?.onResultError(" ${t.message}", ConstantsApp.NET.WS_MOST_POPULAR_GET)
            }
        })
    }

}