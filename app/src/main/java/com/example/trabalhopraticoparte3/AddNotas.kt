package com.example.trabalhopraticoparte3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddNotas : AppCompatActivity() {

    private lateinit var tituloText: EditText
    private lateinit var notasText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notas)

        tituloText = findViewById(R.id.input_titulo)
        notasText = findViewById(R.id.input_notas)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(tituloText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY_CITY, tituloText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_COUNTRY, notasText.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_CITY = "com.example.android.city"
        const val EXTRA_REPLY_COUNTRY = "com.example.android.country"
    }
}