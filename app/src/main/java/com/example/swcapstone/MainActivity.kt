package com.example.swcapstone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.swcapstone.R.id
import com.example.swcapstone.R.layout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_welcome)

        auth = FirebaseAuth.getInstance()

        val welcomeText: TextView = findViewById(id.welcomeText)
        val logoutButton: Button = findViewById(id.logoutButton)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            welcomeText.text = "환영합니다. ${currentUser.email} 님"
        } else {
            // Redirect to Login Activity if not logged in
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainScreenActivity::class.java))
            finish()
        }, 3000)  // 3000 milliseconds or 3 seconds


        logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
