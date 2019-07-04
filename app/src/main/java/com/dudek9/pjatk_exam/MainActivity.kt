package com.dudek9.pjatk_exam

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.content_main2.*

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var ctx: Context
        var handler=Handler()
        lateinit var progress:View
        lateinit var mainActivity: MainActivity
    }

    var quizList = ArrayList<QuestionItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ctx = applicationContext
        mainActivity=this
        setContentView(R.layout.activity_main2)
        progress=progressBar
        setSupportActionBar(toolbar)
        launchExam()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==Activity.RESULT_OK){
            val fm:FragmentManager=supportFragmentManager

            val summary=SummaryFragment(Exam.points)
            fm.beginTransaction().add(R.id.container,summary).commit()
            //TODO
        }
    }

    private fun launchExam() {
        intent = Intent(this, Exam::class.java)

        MADbutton.setOnClickListener {
//            TODO
        }
        TAKbutton.setOnClickListener {
            val comunication: Comunication = Comunication()
            comunication.execute()
            TAKbutton.setClickable(false)
        }
    }

    private fun comunication(subiect: String) {

        var ask: String = ""
        var answers: ArrayList<String> = ArrayList()
        var positive: ArrayList<Boolean> = ArrayList()

        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("Quiz_Set").document("guest1")
        docRef.get().addOnSuccessListener { document ->
            val data = document.data
            ask = data?.get("ask").toString()
            answers = data?.get("answers") as ArrayList<String>
            positive = data?.get("positive") as ArrayList<Boolean>
        }

        quizList.add(QuestionItem(ask, answers, positive))
    }
}
