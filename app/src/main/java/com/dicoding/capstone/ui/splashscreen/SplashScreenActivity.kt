package com.dicoding.capstone.ui.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstone.R
import com.dicoding.capstone.core.Constant.SPLASH_SCREEN_DELAY
import com.dicoding.capstone.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this, MainActivity::class.java)).also {
                finish()
            }
        }, SPLASH_SCREEN_DELAY.toLong())
    }

}