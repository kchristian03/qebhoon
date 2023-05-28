package dev.kchr.se.retrovit

import dev.kchr.se.helper.Const
import dev.kchr.se.model.CreateNoteRequest
import dev.kchr.se.model.CreateNoteResponse
import dev.kchr.se.model.DataPingResponse
import dev.kchr.se.model.DeleteNoteResponse
import dev.kchr.se.model.EditNoteRequest
import dev.kchr.se.model.EditNoteResponse
import dev.kchr.se.model.ReadNoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface RestApi {
    //ping server (for delay splashscreen)
    @GET(Const.PING_URL)
    suspend fun pingServer(): Response<DataPingResponse>

    //get-note

    //create-note
    @Headers("Content-Type: application/json")
    @POST(Const.CREATE_NOTE_URL)
    suspend fun createNote(@Body createNoteRequest: CreateNoteRequest): Response<CreateNoteResponse>

    //get-note
    @GET(Const.GET_NOTE_URL)
    suspend fun getNote(): Response<ReadNoteResponse>

    //edit-note
    @Headers("Content-Type: application/json")
    @PUT(Const.EDIT_NOTE_URL)
    suspend fun editNote(@Path("id") id: Int, @Body editNoteRequest: EditNoteRequest): Response<EditNoteResponse>

    //delete-note
    @DELETE(Const.DELETE_NOTE_URL)
    suspend fun deleteNote(@Path("id") id: Int): Response<DeleteNoteResponse>



    companion object {
        fun getApi(): RestApi? {
            return ServiceBuilder.buildService?.create(RestApi::class.java)
        }
    }
}