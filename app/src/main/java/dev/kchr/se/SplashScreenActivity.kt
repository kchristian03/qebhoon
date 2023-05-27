package dev.kchr.se

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import dev.kchr.se.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = intent
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}