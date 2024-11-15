package com.example.appquiz.model

data class Quiz(
    var id :Int,
    var question :String,
    var options :ArrayList<String>,
    var answer :String
    )
