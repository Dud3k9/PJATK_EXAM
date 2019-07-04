package com.dudek9.pjatk_exam

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.AlphaAnimation
import android.view.animation.Animation

import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.content_start.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        setSupportActionBar(toolbar)
        var anim:Animation=AlphaAnimation(0f,1f)
        anim.duration=1000
        logo.startAnimation(anim)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                var intent= Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
    }



}
