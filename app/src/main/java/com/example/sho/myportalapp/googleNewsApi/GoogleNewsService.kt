package com.example.sho.myportalapp.googleNewsApi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * TODO クラス説明
 *
 * Created by sho on 2018/03/23.
 */
class GoogleNewsService {
    companion object {
        fun createService(): GoogleNewsClient {
            val retro = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            return retro.create(GoogleNewsClient::class.java)
        }
    }
}