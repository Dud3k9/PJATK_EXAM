package com.dudek9.pjatk_exam

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.summary_fragment.*

class SummaryFragment(points:Int) : Fragment(){

    private val points: Int=points


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.summary_fragment,container,false)
    }

    override fun onStart() {
        super.onStart()
        summary.setText(""+points)

        close.setOnClickListener {
            var fm=MainActivity.mainActivity.supportFragmentManager
            fm.beginTransaction().remove(this).commit()
        }
    }


}