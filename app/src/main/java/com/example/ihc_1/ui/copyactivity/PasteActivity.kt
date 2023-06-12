package com.example.ihc_1.ui.copyactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.ihc_1.R

class PasteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paste)

        var resultText = findViewById<TextView>(R.id.output_text)
        var finalString = intent.getStringExtra("sent_text_id")
        resultText.text = finalString

        var backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener(){
            startActivity(Intent(this, CopyFragment::class.java))
        }
    }
}