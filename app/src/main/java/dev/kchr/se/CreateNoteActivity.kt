package dev.kchr.se

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import dev.kchr.se.controller.NoteController
import dev.kchr.se.databinding.ActivityCreateNoteBinding
import dev.kchr.se.helper.GlobalVar
import dev.kchr.se.model.BaseResponse
import dev.kchr.se.model.CreateNoteResponse
import dev.kchr.se.model.Data
import dev.kchr.se.model.DeleteNoteResponse
import dev.kchr.se.model.EditNoteResponse
import java.util.Calendar
import java.util.*

class CreateNoteActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityCreateNoteBinding
    private var position = -1
    private var id = -1
    private val viewModel by viewModels<NoteController>()

    private var dueSave = ""
    private var dueView = ""

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        @Suppress("DEPRECATION") window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()
        binding.progressBarcreatenote.visibility = View.GONE
        binding.deletenoteFAB.visibility = View.GONE
        GetIntent()

        if (position != -1) {
            binding.deletenoteFAB.visibility = View.VISIBLE
        }
        Listener()

        viewModel.createNoteResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    processCreateNote(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
            }
        }

        viewModel.editNoteResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    processEditNote(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
            }
        }

        viewModel.deleteNoteResult.observe(this) {
            when (it) {
                is BaseResponse.Loading -> {
                }

                is BaseResponse.Success -> {
                    processDeleteNote(it.data)
                }

                is BaseResponse.Error -> {
                    processError(it.msg)
                }
            }
        }
    }

    private fun processError(msg: String?) {
        showToast("$msg")
    }

    private fun processCreateNote(data: CreateNoteResponse?) {
        if (data != null) {
            showToast(data.message)
        }
    }

    private fun processEditNote(data: EditNoteResponse?) {
        if (data != null) {
            showToast(data.message)
        }
    }

    private fun processDeleteNote(data: DeleteNoteResponse?) {
        if (data != null) {
            showToast(data.message)
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun Listener() {
        binding.createNoteFAB.setOnClickListener() {
            val title = binding.createnoteTitle.text.toString().trim()
            val content = binding.createnoteContent.text.toString().trim()
            val due = dueSave.toString().trim()
            binding.progressBarcreatenote.visibility = View.VISIBLE
            if (position != -1) {
                //yang ini edit data note
                val id = id
                viewModel.editNote(id, title, content, due)
                binding.progressBarcreatenote.visibility = View.GONE
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                //yang ini create data note
                viewModel.createNote(title, content, due)
                binding.progressBarcreatenote.visibility = View.GONE
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        binding.deletenoteFAB.setOnClickListener() {
            //delete data note
            val id = id
            viewModel.deleteNote(id)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        binding.createNoteDue.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if (position != -1) {
            id = intent.getIntExtra("id", -1)
            val note = GlobalVar.listNote[position]
            display(note)
        }
    }

    private fun display(note: Data) {
        binding.createnoteTitle.setText(note.Title)
        binding.createnoteContent.setText(note.Description)
        binding.createNoteDue.setText(note.Due)

    }

    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year
        getDateTimeCalendar()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
        dueSave = "$savedYear-$savedMonth-$savedDay" + "$savedHour:$savedMinute:00"
        showToast(dueSave)
        dueView = "$savedDay/$savedMonth/$savedYear $savedHour:$savedMinute"
    }
}