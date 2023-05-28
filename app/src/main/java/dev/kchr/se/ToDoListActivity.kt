package dev.kchr.se

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import dev.kchr.se.adapter.ToDoAdapter
import dev.kchr.se.controller.NoteController
import dev.kchr.se.databinding.ActivityTodolistBinding
import dev.kchr.se.helper.GlobalVar
import dev.kchr.se.model.BaseResponse
import dev.kchr.se.model.ReadNoteResponse

class ToDoListActivity : AppCompatActivity(), CardListener {
    private lateinit var binding: ActivityTodolistBinding
    private lateinit var todoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodolistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.makeNoteTextView.visibility = View.GONE
        stopLoading()
        binding.addNotesBTN.setOnClickListener {
            val myIntent = Intent(this, CreateNoteActivity::class.java)
            startActivity(myIntent)
        }

        binding.toolbarNote.title = "Qebhoon To Do List"
        binding.toolbarNote.setNavigationOnClickListener {
            onBackPressed()
        }
        val controller by viewModels<NoteController>()
        init(controller)

    }

    private fun init(controller: NoteController) {
        controller.getNote()
        controller.getNoteResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading()
                }
                is BaseResponse.Success -> {
                    stopLoading()
                    processGetNote(it.data)
                    if (GlobalVar.listNote.isEmpty()) {
                        binding.makeNoteTextView.visibility = View.VISIBLE
                    } else {
                        binding.makeNoteTextView.visibility = View.GONE
                    }
                }
                is BaseResponse.Error -> {
                    stopLoading()
                    processError(it.msg)
                }
                else -> {
                    stopLoading()
                }
            }
        }
    }
    private fun display() {
        todoAdapter = ToDoAdapter(GlobalVar.listNote, this)
        binding.notesRV.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesRV.adapter = todoAdapter
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun processError(msg: String?) {
        showToast("$msg")
    }

    private fun processGetNote(data: ReadNoteResponse?) {
        GlobalVar.listNote.clear()
        if (data != null) {
            showToast(data.message)
            GlobalVar.listNote.addAll(data.data)
        }
        display()
    }

    private fun stopLoading() {
        binding.progressBarNote.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressBarNote.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
//        binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        ToDoAdapter(GlobalVar.listNote, this).notifyDataSetChanged()
    }

    override fun onCardClick(position: Int, id: Int) {
        val myIntent = Intent(this, CreateNoteActivity::class.java).apply {
            putExtra("position", position); putExtra("id", id)
        }
        startActivity(myIntent)
    }

    override fun onCardClicked(view: View, position: Int) {
        val myIntent = Intent(this, CreateNoteActivity::class.java).apply {
            putExtra("position", position)
        }
        startActivity(myIntent)
    }
}