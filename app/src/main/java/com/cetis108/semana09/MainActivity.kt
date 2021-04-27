package com.cetis108.semana09

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonOpenInBrowser = findViewById<ImageButton>(R.id.imageButtonMainOpenInBrowser)
        buttonOpenInBrowser.setOnClickListener {
            val url = "https://" + findViewById<EditText>(R.id.editTextMainUrl).text.toString()
            if (URLUtil.isValidUrl(url) && Patterns.WEB_URL.matcher(url).matches()) {
                val intent = Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("$url")
                }
                startActivity(intent)
            } else {
                Toast.makeText(this, "Dirección incorrecta $url", Toast.LENGTH_SHORT).show()
            }
        }
    }
}