package com.cetis108.semana09

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
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

    // cargar el menu en este activity
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // definir las acciones de cada item del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
                // when es equivalente al switch case de c#
        when (item!!.itemId) {
            R.id.itemMenuMainTakePhoto -> {
                takePhoto(null)
            }
            R.id.itemMenuMainSendEmail -> {
                sendEmail(null)
            }
            R.id.itemMenuMainSendSms -> {
                sendSms(null)
            }
            R.id.itemMenuMainAboutUs -> {
                showAboutUs()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun showAboutUs() {
        // TODO: 30/04/21 Implementar el método para que muestre un activity con tu nombre completo,
        //       grupo, numero de control, carrera o especialidad
    }

    fun sendEmail(view: View?) {
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
        } catch(ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendSms(view: View?) {
        val phoneNumber = "6871234567"
        val message = "Hola desde Android con Kotlin"
        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("sms:$phoneNumber")
            putExtra("sms_body", message)
        }

        try {
            startActivity(intent)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun takePhoto(view: View?) {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")

        try {
            startActivity(intent)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
        }
    }

    fun dialPhoneNumber(view: View?) {
        val phoneNumber = findViewById<EditText>(R.id.editTextMainPhone).text.toString()
        if (phoneNumber.isNullOrEmpty()) {
            Toast.makeText(this, "Ingresa un número telefónico para marcar", Toast.LENGTH_SHORT)
                .show()
        } else {
            val intent = Intent().apply {
                data = Uri.parse("tel:$phoneNumber")
                action = Intent.ACTION_DIAL
            }

            try {
                startActivity(intent)
            } catch (ex: Exception) {
                ex.printStackTrace()
                Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun openInBrowser(view: View?) {
        val url = "https://" + findViewById<EditText>(R.id.editTextMainUrl).text.toString()
        if (URLUtil.isValidUrl(url) && Patterns.WEB_URL.matcher(url).matches()) {
            val intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse("$url")
            }

            try {
                startActivity(intent)
            } catch (ex: Exception) {
                ex.printStackTrace()
                Toast.makeText(this, "Error: ${ex.toString()}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Dirección incorrecta $url", Toast.LENGTH_SHORT).show()
        }
    }
}