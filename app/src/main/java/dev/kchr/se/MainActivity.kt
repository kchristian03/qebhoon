package dev.kchr.se

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.kchr.se.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan ActivityMainBinding untuk menghubungkan tampilan layout dengan activity
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Menetapkan tampilan root dari layout yang dihubungkan ke setContentView
        setContentView(binding.root)

        binding.createbtn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        binding.viewNotebtn.setOnClickListener {
            val intent = Intent(this, ToDoListActivity::class.java)
            startActivity(intent)
        }
    }
}
