package com.example.sho.myportalapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

import java.util.ArrayList

/**
 * TODO クラス説明
 *
 *
 * Created by sho on 2017/11/13.
 */

class NewsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    // ViewPagerに登録する全アイテム数を返す
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titleList[position]
    }
}
