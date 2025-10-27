package com.ramazan.testproject.view.activity

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.ramazan.testproject.R
import androidx.core.net.toUri
import com.ramazan.testproject.view.browser.WebViewActivity

class AuthActivity : AppCompatActivity(R.layout.activity_sign_in) {

    private val EMAIL = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnSignIn = findViewById<Button>(R.id.btnSignIn)
        val btnOk = findViewById<ImageButton>(R.id.ibOK)
        val btnVK = findViewById<ImageButton>(R.id.ibVK)

        btnSignIn.isEnabled = false
        btnSignIn.alpha = 0.5f
        val updateButtonState = {
            val email = etEmail.text.toString().trim()
            val pass = etPassword.text.toString()
            val valid = isValidEmailMask(email) && isValidPassword(pass)
            btnSignIn.isEnabled = valid
            btnSignIn.alpha = if (valid) 1f else 0.5f
        }

        etEmail.doAfterTextChanged { updateButtonState() }
        etPassword.doAfterTextChanged { updateButtonState() }

        btnSignIn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btnVK.setOnClickListener {
            openUrl(this,"https://vk.com/")
        }

        btnOk.setOnClickListener {
            openUrl(this,"https://m.ok.ru/")
        }

        updateButtonState()
    }

    fun openUrl(context: Context, url: String) {
        if (url.isBlank()) return
        val uri = url.toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            context.startActivity(Intent(context, WebViewActivity::class.java).putExtra("url", url))
        }
    }

    private fun isValidEmailMask(email: String): Boolean =
        email.isNotEmpty() && EMAIL.matches(email)

    private fun isValidPassword(password: String): Boolean =
        password.length >= 6
}