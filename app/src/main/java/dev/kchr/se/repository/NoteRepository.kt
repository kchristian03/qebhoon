package dev.kchr.se.repository

import dev.kchr.se.model.CreateNoteRequest
import dev.kchr.se.model.CreateNoteResponse
import dev.kchr.se.model.DeleteNoteResponse
import dev.kchr.se.model.EditNoteRequest
import dev.kchr.se.model.EditNoteResponse
import dev.kchr.se.model.ReadNoteResponse
import dev.kchr.se.retrovit.RestApi
import retrofit2.Response

class NoteRepository {
    suspend fun createNote(createNoteRequest: CreateNoteRequest): Response<CreateNoteResponse>?{
        return RestApi.getApi()?.createNote(createNoteRequest = createNoteRequest)
    }

    suspend fun getNote(): Response<ReadNoteResponse>?{
        return RestApi.getApi()?.getNote()
    }

    suspend fun editNote(id: Int, editNoteRequest: EditNoteRequest): Response<EditNoteResponse>?{
        return RestApi.getApi()?.editNote(id = id, editNoteRequest = editNoteRequest)
    }

    suspend fun deleteNote(id: Int): Response<DeleteNoteResponse>?{
        return RestApi.getApi()?.deleteNote(id = id)
    }
}