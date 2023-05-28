package dev.kchr.se.helper

import dev.kchr.se.model.Data

class GlobalVar {

    companion object{
        val STORAGE_PERMISSION_CODE: Int = 100
//        var listLogs = ArrayList<Note>()
        var currrentUserStreak = 0
        var lastIncrementDate: String? = null

//        var userData: DataUserResponse? = null
        var listNote = ArrayList<Data>()
    }

}