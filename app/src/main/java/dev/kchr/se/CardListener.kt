package dev.kchr.se

import android.view.View

interface CardListener {
    fun onCardClick(position: Int, id: Int)
    fun onCardClicked(view: View, position: Int)
}