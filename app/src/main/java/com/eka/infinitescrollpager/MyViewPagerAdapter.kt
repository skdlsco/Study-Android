package com.eka.infinitescrollpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter

class MyViewPagerAdapter : FragmentStatePagerAdapter {

    var num = 1000

    constructor(fm: FragmentManager?) : super(fm)

    override fun getItem(position: Int): Fragment = BlankFragment.newInstance(num + position - 1) // 다같은 뷰를 띄우므로 다같은걸 return


    override fun getCount(): Int = 3 // 사용자가 보고있는것은 가운데만 있게하고 양옆으로 가면 다시 가운데로 오므로 3 ~ 5면됨

    override fun getItemPosition(`object`: Any): Int = PagerAdapter.POSITION_NONE
}