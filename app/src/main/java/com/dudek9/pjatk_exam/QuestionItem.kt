package com.dudek9.pjatk_exam

import java.io.Serializable

data class QuestionItem(
    var ask:String="ask",
    var answer: ArrayList<String> = ArrayList<String>(),
    var positive:ArrayList<Boolean> = ArrayList()): Serializable