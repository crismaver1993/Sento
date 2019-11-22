package com.dot7.firebase.codelabs.models
import androidx.annotation.Keep

@Keep
data class Game  (
       var name: String = "",
       var score: Int = 0,
       var uuid: String = "",
       var photoUrl: String = ""
    )