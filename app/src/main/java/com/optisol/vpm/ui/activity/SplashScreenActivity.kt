package com.optisol.vpm.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.optisol.vpm.R
import com.optisol.vpm.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivitySplashScreenBinding.inflate(layoutInflater).root)
        supportActionBar?.hide()
        window?.statusBarColor = resources.getColor(R.color.off_black)

        coroutineScope.launch {
            delay(2000)
            startActivity(Intent(this@SplashScreenActivity,MainActivity::class.java))
            finish()

        }

    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
    }
}