package com.example.mybuttonprogram

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var leftBtn = findViewById<Button>(R.id.leftBtn)
        var rightBtn = findViewById<Button>(R.id.rightBtn)
        var totalScore = findViewById<TextView>(R.id.scoreTxt)

        startRound(totalScore)

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

    private fun startRound(totalScore: TextView?) {
        val leftVal = random_number()
        val rightVal = random_number()

        findViewById<Button>(R.id.leftBtn).text = leftVal.toString()
        findViewById<Button>(R.id.rightBtn).text = rightVal.toString()

        if (totalScore != null) {
            totalScore.text = "Score: $score"
        }
    }

    private fun random_number() :Int {
        return Random.nextInt(1, 99)
    }

    private fun checkScore(score: Int, threshold: Int) {
        if (score % threshold == 0 && score > 0)
            Toast.makeText(applicationContext, "Congratulations! You have reached a score of $score", Toast.LENGTH_LONG).show()
    }

    private fun checkAnswer(selectedNumber: Int, otherNumber: Int, totalScore: TextView) {
        if (selectedNumber > otherNumber) {
            score++
            totalScore.text = "Score: $score"
        } else {
            Toast.makeText(applicationContext, "Wrong answer. Please try again", Toast.LENGTH_LONG).show()
            score = 0
        }

        checkScore(score, 10)
        checkScore(score, 20)
        checkScore(score, 30)
        checkScore(score, 40)
        checkScore(score, 50)
        startRound(totalScore)
    }

}