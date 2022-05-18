package tests.section3

class Game {

    var score = 0
        private set

    var highScore = 0
        private set

    fun incrementScore() {
        score++


        if(score > highScore){
            highScore++
        }
    }

}

fun main() {

    println("Game")
    shouldIncrementHighScore_WhenIncrementingScore()
}

fun shouldIncrementHighScore_WhenIncrementingScore(){

    val game = Game()

    game.incrementScore()

    if(game.highScore ==1){
        print("Success")
    }else{
        throw java.lang.AssertionError("Score and HighScore don't match")
    }

}