package dev.kchr.se

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kchr.se.databinding.ActivityMainBinding
import dev.kchr.se.databinding.ActivitySplashScreenBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}