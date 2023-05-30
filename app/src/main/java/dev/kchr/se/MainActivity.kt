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

        // Intent untuk mengarah ke page Create Note Page
        binding.createbtn.setOnClickListener {
            val intent = Intent(this, CreateNoteActivity::class.java)
            startActivity(intent)
        }

        // Intent untuk mengarah ke page Read Note Page
        binding.viewNotebtn.setOnClickListener {
            val intent = Intent(this, ToDoListActivity::class.java)
            startActivity(intent)
        }
    }
}
