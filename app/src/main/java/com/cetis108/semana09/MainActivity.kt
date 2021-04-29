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

    // TODO: 29/04/21 Agregar un menú a la aplicación

    fun sendEmail(view: View) {
        val myEmail = "bidkar@cetis108.edu.mx"
        val subject = "Hola desde Android con Kotlin"
        val text = "Mensaje de prueba desde Android"
        val recepients = arrayOf("uno@numero.com", "dos@numero.com")
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            data = Uri.parse(myEmail)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, text)
            putExtra(Intent.EXTRA_EMAIL, recepients)
            type = "text/plain"
        }

        try {
            startActivity(Intent.createChooser(intent, "Elige una aplicación"))
            finish()
        } catch(ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendSms(view: View) {
        val phoneNumber = "6871234567"
        val message = "Hola desde Android con Kotlin"
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("sms:$phoneNumber")
            putExtra("sms_body", message)
        }

        try {
            startActivity(intent)
            finish()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun takePhoto(view: View) {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")

        try {
            startActivity(intent)
            finish()
        } catch (ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialPhoneNumber(view: View) {
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