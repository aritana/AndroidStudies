package com.example.fortuneball2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.fortuneball2.databinding.ActivityMainBinding
import java.util.*


private var fortuneList = arrayOf(
    "Don’t count on it",
    "Ask again later",
    "You can rely on it",
    "Without a doubt",
    "Outlook is not so good",
    "It's decidedly so",
    "Signs point to yes",
    "Yes, definitely",
    "Yes",
    "My sources say NO"
)

private lateinit var fortuneText: TextView
private lateinit var generateFortuneButton: Button
private lateinit var fortuneBallImage: ImageView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// 2:
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
// 3:
        fortuneText = findViewById<View>(R.id.fortuneText) as TextView
        fortuneBallImage = findViewById<View>(R.id.fortunateImage) as ImageView
        generateFortuneButton = findViewById<View>(R.id.fortuneButton) as Button

// 4:
        generateFortuneButton.setOnClickListener {
            // 5:
            val index = Random().nextInt(fortuneList.size)
            fortuneText.setText(fortuneList[index])
            // 6:
            YoYo.with(Techniques.Swing)
                .duration(500)
                .playOn(fortuneBallImage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}