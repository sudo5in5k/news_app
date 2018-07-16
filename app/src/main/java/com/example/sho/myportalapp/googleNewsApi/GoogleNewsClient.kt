package com.example.sho.myportalapp.googleNewsApi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * TODO クラス説明
 *
 * Created by sho on 2018/03/22.
 */
public interface GoogleNewsClient {
    @GET("/v2/everything?apiKey=YOUR_API_KEY")
    fun addQuery(@Query("q") q: String,
                 @Query("sortBy") sortBy: String,
                 @Query("pageSize") pageSize: Int): Call<GoogleNewsSources>

}