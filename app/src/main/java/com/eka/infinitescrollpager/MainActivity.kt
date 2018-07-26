package com.eka.infinitescrollpager

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pagerAdapter = MyViewPagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                if (state == 0) {
                    pagerAdapter.num += viewPager.currentItem - 1 // 값 변경
                    viewPager.setCurrentItem(1, false) // item에 중간 값을 넣는다.
                    pagerAdapter.notifyDataSetChanged()
                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) { // 여기서 바로 하면 스크롤이 중간에 끊긴다.
            }
        })
        viewPager.setCurrentItem(1, false)
    }
}
