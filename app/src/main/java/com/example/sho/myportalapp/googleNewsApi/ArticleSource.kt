package com.example.sho.myportalapp.googleNewsApi

import android.net.Uri

/**
 * TODO クラス説明
 *
 * Created by sho on 2018/03/23.
 */
data class ArticleSource(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?
) {
    fun getActualImageUrl():Uri? {
        urlToImage?.apply {
            return Uri.parse(this)
        }
        return null
    }
}