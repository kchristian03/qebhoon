package dev.kchr.se

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kchr.se.databinding.ActivityMainBinding
import dev.kchr.se.databinding.ActivityTodolistBinding

class todolistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTodolistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodolistBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}