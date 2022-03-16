package com.raywenderlich.android.cocktails.game.model

import org.junit.Assert
import org.junit.Test

class GameUnitTests {

    @Test //given when then. Arrange, act, assert
    fun whenIncrementingScore_shouldIncrementCurrentScore() {

        val game = Game()
        game.incrementScore()
        Assert.assertEquals("Current score should have been 1", 1, game.currentScore)

    }

    @Test
    fun whenIncrementingScore_aboveHighScore_ShouldAlsoIncrementHighestScore() {

        val game = Game(0)
        game.incrementScore()
        Assert.assertEquals("Highest Score should also be incremented", 1, game.highestScore)


    }
    //JUnit annotations

}