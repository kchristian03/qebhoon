package dev.kchr.se.controller

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.kchr.se.model.BaseResponse
import dev.kchr.se.model.CreateNoteRequest
import dev.kchr.se.model.CreateNoteResponse
import dev.kchr.se.model.DeleteNoteResponse
import dev.kchr.se.model.EditNoteRequest
import dev.kchr.se.model.EditNoteResponse
import dev.kchr.se.model.ReadNoteResponse
import dev.kchr.se.repository.NoteRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

class NoteController(application: Application) : AndroidViewModel(application) {
    private val noteRepo = NoteRepository()
    val createNoteResult: MutableLiveData<BaseResponse<CreateNoteResponse>> = MutableLiveData()
    val getNoteResult : MutableLiveData<BaseResponse<ReadNoteResponse>> = MutableLiveData()
    val editNoteResult : MutableLiveData<BaseResponse<EditNoteResponse>> = MutableLiveData()
    val deleteNoteResult: MutableLiveData<BaseResponse<DeleteNoteResponse>> = MutableLiveData()

    fun createNote(title: String, description: String, due: String){
        createNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
//                val token = "Bearer $token"
                val createNoteRequest = CreateNoteRequest(
                    title = title,
                    description = description,
                    due = due,
                )
                val response = noteRepo.createNote(createNoteRequest)
                if(response?.code() == 200){
                    createNoteResult.value = BaseResponse.Success(response.body())
                }else if (response?.code() == 400) {
                    createNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    createNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception){
                createNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun getNote () {
        getNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
//                val token = "Bearer $token"
                val response = noteRepo.getNote()
                if (response?.code() == 200) {
                    getNoteResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    getNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    getNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                getNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun editNote (id: Int, title: String, description: String, due: String){
        editNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
//                val token = "Bearer $token"
                val editNoteRequest = EditNoteRequest(
                    title = title,
                    description = description,
                    due = due,
                )
                val response = noteRepo.editNote(id, editNoteRequest)
                if (response?.code() == 200) {
                    editNoteResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    editNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    editNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                editNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }

    fun deleteNote (id: Int) {
        deleteNoteResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
//                val token = "Bearer $token"
                val response = noteRepo.deleteNote(id)
                if (response?.code() == 200) {
                    deleteNoteResult.value = BaseResponse.Success(response.body())
                } else if (response?.code() == 400) {
                    deleteNoteResult.value = BaseResponse.Error(
                        response.errorBody()?.let { JSONObject(it.string()).getString("message") })
                } else {
                    deleteNoteResult.value = BaseResponse.Error(
                        response?.errorBody()?.let { JSONObject(it.string()).getString("message") })
                }
            } catch (ex: Exception) {
                deleteNoteResult.value = BaseResponse.Error(ex.message)
            }
        }
    }
}