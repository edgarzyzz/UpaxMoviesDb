package com.gogaedd.upaxmovies_gge.core.persistence.net

interface NetListener {
    fun onResultOk(any:Any, code: Int)
    fun onResultError(meesageError:String, code: Int)

}