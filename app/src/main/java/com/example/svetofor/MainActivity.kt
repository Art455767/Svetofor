package com.example.svetofor

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var currentLight = 0
    private lateinit var handler: Handler
    private val delay = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val redLight: View = findViewById(R.id.redLight)
        val yellowLight: View = findViewById(R.id.yellowLight)
        val greenLight: View = findViewById(R.id.greenLight)
        val buttonChange: Button = findViewById(R.id.buttonChange)

        redLight.visibility = View.VISIBLE
        handler = Handler(Looper.getMainLooper())
        startAutomaticSwitch()

        buttonChange.setOnClickListener {
            handler.removeCallbacksAndMessages(null)
            switchLight()
            startAutomaticSwitch()
        }
    }

    private fun startAutomaticSwitch() {
        handler.postDelayed({
            switchLight()
            startAutomaticSwitch()
        }, delay)
    }

    private fun switchLight() {

        val redLight: View = findViewById(R.id.redLight)
        val yellowLight: View = findViewById(R.id.yellowLight)
        val greenLight: View = findViewById(R.id.greenLight)

        redLight.visibility = View.INVISIBLE
        yellowLight.visibility = View.INVISIBLE
        greenLight.visibility = View.INVISIBLE

        currentLight = (currentLight + 1) % 3

        when (currentLight) {
            0 -> redLight.visibility = View.VISIBLE
            1 -> yellowLight.visibility = View.VISIBLE
            2 -> greenLight.visibility = View.VISIBLE
        }
    }
}