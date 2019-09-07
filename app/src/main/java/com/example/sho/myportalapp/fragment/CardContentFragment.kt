package com.example.sho.myportalapp.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sho.myportalapp.adapter.NewsRecyclerAdapter
import com.example.sho.myportalapp.R
import com.example.sho.myportalapp.googleNewsApi.ArticleSource
import com.example.sho.myportalapp.googleNewsApi.GoogleNewsService
import com.example.sho.myportalapp.googleNewsApi.GoogleNewsSources

import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

import java.util.ArrayList

/**
 * ニュースタブに表示されているニュースを表示するためのフラグメント
 *
 *
 * Created by sho on 2017/11/13.
 */

class CardContentFragment : Fragment() {


    lateinit private var recyclerView: RecyclerView

    companion object {
        @JvmStatic
        fun newInstance(name: String) = CardContentFragment().apply {
            arguments = Bundle(1).apply {
                putString("TAB_NAME", name)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recycler_view, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val targetTabName = arguments.getString("TAB_NAME")
        val data = fetchData(targetTabName)
        val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(context.getDrawable(R.drawable.divider))
        with(view) {
            recyclerView = this!!.findViewById<RecyclerView>(R.id.my_recycler_view).apply {
                adapter = NewsRecyclerAdapter(context, data)
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                addItemDecoration(itemDecoration)
            }
        }
    }

    /**
     * @param tabName: 指定したキーワード = タブ名
     */
    private fun fetchData(tabName: String): ArrayList<ArticleSource> {
        val service = GoogleNewsService.createService().addQuery(tabName, "popularity", 100)
        Log.d("ushi", service.toString())
        var arrayList = ArrayList<ArticleSource>()
        val item = object : Callback<GoogleNewsSources> {
            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             */
            override fun onFailure(call: Call<GoogleNewsSources>?, t: Throwable?) {
                Log.d("ushiTest", t.toString())
                Log.d("ushi2", "hoge2")
            }

            /**
             * Invoked for a received HTTP response.
             *
             *
             * Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call [Response.isSuccessful] to determine if the response indicates success.
             */
            override fun onResponse(call: Call<GoogleNewsSources>?, response: Response<GoogleNewsSources>?) {
                Log.d("ushi2", "hoge")
                Log.d("ushiTest", "onResponseが返されました body:" + response?.body())

                response?.body()?.articles?.forEach { arrayList.add(it) }
                recyclerView.adapter.notifyDataSetChanged()
            }

        }
        service.enqueue(item)
        return arrayList
    }
}


