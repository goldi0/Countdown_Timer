package com.goldi.countdowntimer

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvCountdown: TextView
    private lateinit var btnStart: Button
    private lateinit var btnReset: Button

    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMillis: Long = 0
    private val startTimeInMillis: Long = 600000 // 10 minutes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCountdown = findViewById(R.id.tvCountdown)
        btnStart = findViewById(R.id.btnStart)
        btnReset = findViewById(R.id.btnReset)

        btnStart.setOnClickListener {
            startCountdown()
        }

        btnReset.setOnClickListener {
            resetCountdown()
        }

        resetCountdown()
    }

    private fun startCountdown() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished
                updateCountdownText()
            }

            override fun onFinish() {
                // Countdown finished
                timeLeftInMillis = 0
                updateCountdownText()
            }
        }.start()
    }

    private fun resetCountdown() {
        timeLeftInMillis = startTimeInMillis
        updateCountdownText()
        if (::countDownTimer.isInitialized) {
            countDownTimer.cancel()
        }
    }

    private fun updateCountdownText() {
        val hours = (timeLeftInMillis / 3600000) % 24
        val minutes = (timeLeftInMillis / 60000) % 60
        val seconds = (timeLeftInMillis / 1000) % 60

        val timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        tvCountdown.text = timeFormatted
    }
}
