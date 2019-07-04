package com.dudek9.pjatk_exam

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_exam.*
import kotlinx.android.synthetic.main.content_exam.*
import kotlin.collections.ArrayList

class Exam : AppCompatActivity() {

    companion object{
        var points=0
    }
    private var level=0
    private val questionList by lazy { intent.extras.get("QUIZ_SET") as ArrayList<QuestionItem> }
    private lateinit var currentQuestionItem: QuestionItem
    private val questionItereator by lazy { questionList.iterator() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam)
        setSupportActionBar(toolbar)
        nextQusetion()
        checkButton()
    }

    private fun nextQusetion() {
        if (questionItereator.hasNext()) {
            currentQuestionItem = questionItereator.next()
            setUpButton()
        }else
            returnResaultFromQuiz()
    }

    private fun returnResaultFromQuiz() {
        intent= Intent()
        //intent.putExtra("points",points)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }


    private fun setUpButton() {

        ques.setText(currentQuestionItem.ask)
        ans_a.setText(currentQuestionItem.answer.get(0))
        ans_b.setText(currentQuestionItem.answer.get(1))
        ans_c.setText(currentQuestionItem.answer.get(2))
        ans_d.setText(currentQuestionItem.answer.get(3))
    }

    private fun checkButton() {
        check.setOnClickListener {
            if (check.text.equals("Sprawdź")) {
                var point: Boolean = true
                if (ans_a.isChecked == currentQuestionItem.positive[0])
                    ans_a.setBackgroundColor(Color.GREEN)
                else
                    point = false
                if (ans_b.isChecked == currentQuestionItem.positive[1])
                    ans_b.setBackgroundColor(Color.GREEN)
                else
                    point = false
                if (ans_c.isChecked == currentQuestionItem.positive[2])
                    ans_c.setBackgroundColor(Color.GREEN)
                else
                    point = false
                if (ans_d.isChecked == currentQuestionItem.positive[3])
                    ans_d.setBackgroundColor(Color.GREEN)
                else
                    point = false
                if (point) {
                    points++
                    summary.setText("" + points)
                }
                check.setText("Dalej")
                setCheckable(false)
            } else {
                if(level<20) {
                    check.setText("Sprawdź")
                    setCheckable(true)
                    uncheckedAll()
                    setDefaultBackground()
                    level++
                    levelT.setText("${level}/20")
                    nextQusetion()
                }
            }
        }
    }

    private fun uncheckedAll() {
        ans_a.setChecked(false)
        ans_b.setChecked(false)
        ans_c.setChecked(false)
        ans_d.setChecked(false)
    }

    private fun setDefaultBackground() {
        ans_a.setBackgroundResource(R.drawable.button_round)
        ans_b.setBackgroundResource(R.drawable.button_round)
        ans_c.setBackgroundResource(R.drawable.button_round)
        ans_d.setBackgroundResource(R.drawable.button_round)
    }


    private fun setCheckable(b: Boolean) {
        ans_a.isClickable=b
        ans_b.isClickable=b
        ans_c.isClickable=b
        ans_d.isClickable=b
    }


}
