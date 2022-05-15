package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result on the screen
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rollButton = findViewById<Button>(R.id.am_rollButton)
        rollButton.setOnClickListener() { rollDice() }

        // Do a dice roll when the app starts
        rollDice()
    }

    /**
     * Roll the dice and update the screen with the result
     */
    private fun rollDice() {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        //verify the number of the side and convert it to an image
        var drawableResource = when (diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else ->{ R.drawable.dice_6}
        }

        // Update the screen with the dice roll
        var diceImage = findViewById<ImageView>(R.id.imageView)
        diceImage.setImageResource(drawableResource)
        diceImage.contentDescription = diceRoll.toString()


    }
}

/**
 * This Dice allows the user to roll the dice
 */
class Dice(private val numSides: Int) {

    /**
     * Roll the dice
     */
    fun roll(): Int {
        return (1..numSides).random()
    }
}