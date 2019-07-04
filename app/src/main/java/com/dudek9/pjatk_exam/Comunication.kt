package com.dudek9.pjatk_exam

import android.app.ProgressDialog
import android.content.Intent
import android.os.AsyncTask
import android.util.Log
import android.widget.ProgressBar
import com.google.firebase.firestore.FirebaseFirestore
import io.opencensus.stats.View
import java.util.*
import kotlin.collections.ArrayList

class Comunication :AsyncTask<Void,Void,Void>() {


    var quizList = ArrayList<QuestionItem>()

    override fun onPreExecute() {

        MainActivity.handler.post(object : Runnable {
            override fun run() {
                MainActivity.progress.visibility=android.view.View.VISIBLE
            }
        })

    }

    override fun doInBackground(vararg params: Void?): Void?{
        var ask: String=""
        var answers: ArrayList<String> = ArrayList()
        var positive: ArrayList<Boolean> =ArrayList()

        val db = FirebaseFirestore.getInstance()

        for (i in 1..20) {
            val docRef = db.collection("Quiz_Set").document("guest"+i)
            var data: Map<String, Object>? = null
            docRef.get().addOnSuccessListener { document ->
                data = document.data as Map<String, Object>
            }

            while (data == null) {
                Thread.sleep(10)
            }

            ask = data?.get("ask").toString()
            answers = data?.get("answers") as ArrayList<String>
            positive = data?.get("positive") as ArrayList<Boolean>
            quizList.add(QuestionItem(ask, answers, positive))

        }
        return null
    }

    override fun onPostExecute(result: Void?) {

        var intent:Intent= Intent(MainActivity.ctx,Exam::class.java)
        intent.putExtra("QUIZ_SET",quizList)
        MainActivity.mainActivity.startActivityForResult(intent,1)


        MainActivity.handler.post(object : Runnable {
            override fun run() {
                MainActivity.progress.visibility=android.view.View.GONE
            }
        })
    }
}