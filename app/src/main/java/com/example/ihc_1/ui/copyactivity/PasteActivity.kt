package com.example.ihc_1.ui.copyactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ihc_1.R

class PasteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paste)

        var resultText = findViewById<TextView>(R.id.output_text)
        var finalString = intent.getStringExtra("sent_text_id")
        resultText.text = finalString
    }
}