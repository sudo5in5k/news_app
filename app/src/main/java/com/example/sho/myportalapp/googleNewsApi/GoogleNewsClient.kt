package com.example.sho.myportalapp.googleNewsApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * TODO クラス説明
 *
 * Created by sho on 2018/03/22.
 */
interface GoogleNewsClient {
    @GET("everything?apiKey=3b007aa115c24b6e8f05844c7c33c621")
    fun addQuery(@Query("q") q: String,
                 @Query("sortBy") sortBy: String,
                 @Query("pageSize") pageSize: Int): Call<GoogleNewsSources>

}