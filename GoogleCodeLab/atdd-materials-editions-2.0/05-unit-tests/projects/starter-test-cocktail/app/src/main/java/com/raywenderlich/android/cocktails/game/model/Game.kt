package com.raywenderlich.android.cocktails.game.model

class Game( highestScore:Int = 0) {

    var highestScore = highestScore
        private set
    var currentScore = 0
       private set //the setter is private and has the default implementation
    //if you need to annootate an accessor or change its visibility
    //but you don't need to change the default implementation,
    //you can define the acessor without defining its body

    fun incrementScore() {
        currentScore++

        if (currentScore > highestScore) {
            highestScore = currentScore
        }
    }
}
