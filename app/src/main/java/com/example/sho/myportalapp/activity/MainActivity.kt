package com.example.sho.myportalapp.activity

import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log

import com.example.sho.myportalapp.adapter.NewsPagerAdapter
import com.example.sho.myportalapp.R
import com.example.sho.myportalapp.fragment.CardContentFragment
import com.example.sho.myportalapp.googleNewsApi.TabNameSources

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    private var viewPager: ViewPager? = null
    private var tabPosition: Int = 0

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //added
        val toolbar = findViewById<Toolbar>(R.id.news_toolber)
        setSupportActionBar(toolbar) // activityのアクションバーとして機能するようツールバーを設定

        viewPager = findViewById(R.id.news_view_pager)
        viewPager?.offscreenPageLimit = TabNameSources.tabNameList.size - 1
        val newsPagerAdapter = NewsPagerAdapter(supportFragmentManager)
        val data = TabNameSources.tabNameList

        if (data != null) {
            for (keyword in data) {
                newsPagerAdapter.addFragment(CardContentFragment.newInstance(keyword), keyword)
            }
            viewPager?.adapter = newsPagerAdapter
        }

        val tabLayout = findViewById<TabLayout>(R.id.news_tab_layout)
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.setupWithViewPager(viewPager) // TablayoutとViewPagerを紐付ける
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        tabPosition = tab.position
        Log.d("DEBUG_TAB", "タブ" + tabPosition.toString() + "が選択されました")
        viewPager?.currentItem = tab.position
    }

    override fun onTabUnselected(tab: TabLayout.Tab) {}

    override fun onTabReselected(tab: TabLayout.Tab) {}

}
