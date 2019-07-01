package com.dudek9.pjatk_exam

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class MainActivity : AppCompatActivity() {

    var quizList = ArrayList<QuestionItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(toolbar)
        launchExam()
    }

    private fun launchExam() {
        intent = Intent(this, Exam::class.java)

        MADbutton.setOnClickListener {

            comunication("MAD")

            intent.putExtra("QUIZ_SET", quizList)
            startActivity(intent)
        }
        TAKbutton.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun comunication(subiect:String) {
        var answer = ArrayList<String>()
        var positive = ArrayList<Boolean>()
        answer.add("odp1p")
        positive.add(true)
        answer.add("odp1")
        positive.add(false)
        answer.add("odp1p")
        positive.add(true)
        answer.add("odp1")
        positive.add(false)

        quizList.add(QuestionItem("pytanie?", answer, positive))

        var answer1 = ArrayList<String>()
        var positive1 = ArrayList<Boolean>()
        answer1.add("odp2")
        positive1.add(false)
        answer1.add("odp2p")
        positive1.add(true)
        answer1.add("odp2p")
        positive1.add(true)
        answer1.add("odp2")
        positive1.add(false)

        quizList.add(QuestionItem("pytanie2?", answer1, positive1))

    }


}
