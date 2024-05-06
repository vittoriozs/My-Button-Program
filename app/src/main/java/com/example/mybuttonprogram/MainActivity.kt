package com.example.mybuttonprogram

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mytest.R
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val leftBtn = findViewById<Button>(R.id.leftBtn)
        val rightBtn = findViewById<Button>(R.id.rightBtn)
        val totalScore = findViewById<TextView>(R.id.scoreTxt)

        playGame(totalScore)

        leftBtn.setOnClickListener {
            checkAnswer(leftBtn.text.toString().toInt(), rightBtn.text.toString().toInt(), totalScore)
        }

        rightBtn.setOnClickListener {
            checkAnswer(rightBtn.text.toString().toInt(), leftBtn.text.toString().toInt(), totalScore)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    @SuppressLint("SetTextI18n")
    private fun playGame(totalScore: TextView?) {
        val leftVal = randomNumber()
        val rightVal = randomNumber()

        findViewById<Button>(R.id.leftBtn).text = leftVal.toString()
        findViewById<Button>(R.id.rightBtn).text = rightVal.toString()

        if (totalScore != null) {
            totalScore.text = "Score: $score"
        }
    }

    private fun randomNumber() :Int {
        return Random.nextInt(1, 99)
    }

    @SuppressLint("SetTextI18n")
    private fun checkAnswer(selectedNumber: Int, otherNumber: Int, totalScore: TextView) {
        if (selectedNumber > otherNumber) {
            score++
            totalScore.text = "Score: $score"
        } else {
            Toast.makeText(applicationContext, "Wrong answer. Please try again", Toast.LENGTH_LONG).show()
            score = 0
        }

        for (threshold in 10 until Int.MAX_VALUE step 10) {
            if (score % threshold == 0 && score > 0) {
                Toast.makeText(applicationContext, "Congratulations! You have reached a score of $score", Toast.LENGTH_LONG).show()
            }
        }

        playGame(totalScore)
    }

}