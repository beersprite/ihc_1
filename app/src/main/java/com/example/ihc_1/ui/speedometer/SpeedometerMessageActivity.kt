package com.example.ihc_1.ui.speedometer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.ihc_1.R
import com.example.ihc_1.ui.copyactivity.CopyFragment

class SpeedometerMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speedometer_message)

        var resultText = findViewById<TextView>(R.id.output_speed_text)
        var finalString = intent.getStringExtra("sent_text_id")
        resultText.text = finalString

        var backButton = findViewById<Button>(R.id.speedometer_back_button)
        backButton.setOnClickListener(){
            startActivity(Intent(this, SpeedometerFragment::class.java))
        }
    }
}