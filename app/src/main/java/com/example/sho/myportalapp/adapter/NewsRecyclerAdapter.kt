package com.example.sho.myportalapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.example.sho.myportalapp.R
import com.example.sho.myportalapp.googleNewsApi.ArticleSource
import com.squareup.picasso.Picasso

import java.util.ArrayList

/**
 * TODO クラス説明
 *
 *  RecyclerView.Adapter: Viewの作成と表示されるViewとデータの結びつけをする
 * Created by sho on 2017/11/13.
 */

class NewsRecyclerAdapter(val adapterContext: Context, itemList: ArrayList<ArticleSource>) :
        RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {

    private val mItemList: List<ArticleSource>
    private var mInflater: LayoutInflater? = null

    init {
        mInflater = LayoutInflater.from(adapterContext)
        mItemList = itemList
    }

    // 下のViewHolderとつながっている, layoutの設定
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.context)
        }
        val view = mInflater!!.inflate(R.layout.news_adapter_card_list, parent, false)
        return ViewHolder(view)
    }

    // Layoutの画像や文字を設定
    override fun onBindViewHolder(holder: NewsRecyclerAdapter.ViewHolder, position: Int) {

        val imageUri = mItemList[position]?.getActualImageUrl()

        holder.titleTextView.text = mItemList[position].title
        holder.urlTextView.text = mItemList[position].url
        holder.newsContentSummaryTextView.text = mItemList[position].description

        try {
            Picasso.with(adapterContext).load(imageUri)
                    .placeholder(R.drawable.news_pic).into(holder.urlImageView)
        } catch (e: Exception) {
            holder.urlImageView.setImageResource(R.drawable.news_pic)
        }

        holder.setNewsUrl(mItemList[position].url)
    }


    override fun getItemCount(): Int {
        return mItemList.size
    }

    // Viewへの参照を保持する
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        internal val titleTextView: TextView = view.findViewById(R.id.news_title_name)
        internal val urlTextView: TextView = view.findViewById(R.id.news_url_content)
        internal val newsContentSummaryTextView: TextView = view.findViewById(R.id.news_content_summary)
        internal val urlImageView: ImageView = view.findViewById(R.id.news_thumbnail)

        fun setNewsUrl(url: String?) {
            itemView.setOnClickListener { view ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }
    }

    // アイテムのViewTypeを設定する
    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}
