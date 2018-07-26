package com.eka.infinitescrollpager

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : Fragment() {

    var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.num = arguments!!["num"] as Int
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        textView.text = num.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance(num: Int) =
                BlankFragment().apply {
                    arguments = Bundle().apply {
                        putInt("num", num)
                    }
                }
    }
}
