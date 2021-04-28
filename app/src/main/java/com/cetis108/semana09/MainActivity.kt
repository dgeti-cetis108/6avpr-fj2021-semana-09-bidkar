package com.cetis108.semana09

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.webkit.URLUtil
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun sendSms(view: View) {
        // TODO: 28/04/21 Realizar un intent para enviar mensajes SMS
    }

    fun takePhoto(view: View) {
        // TODO: 28/04/21 Realizar un intent para hacer uso de la cámara del teléfono
    }

    fun dialToPhone(view: View) {
        val phoneNumber = findViewById<EditText>(R.id.editTextMainPhone).text.toString()
        if (phoneNumber.isNullOrEmpty()) {
            Toast.makeText(this, "Ingresa un número telefónico para marcar", Toast.LENGTH_SHORT)
                .show()
        } else {
            val intent = Intent().apply {
                data = Uri.parse("tel:$phoneNumber")
                action = Intent.ACTION_DIAL
            }
            startActivity(intent)
        }
    }

    fun openInBrowser(view: View) {
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