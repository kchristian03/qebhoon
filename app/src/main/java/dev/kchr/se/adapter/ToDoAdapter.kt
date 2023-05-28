package dev.kchr.se.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import dev.kchr.se.CardListener
import dev.kchr.se.R
import dev.kchr.se.databinding.CardTodoBinding
import dev.kchr.se.model.Data
import java.util.*
import kotlin.collections.ArrayList

class ToDoAdapter(val listNote: ArrayList<Data>, val cardListener: CardListener) :
    RecyclerView.Adapter<ToDoAdapter.viewHolder>() {

    class viewHolder (val itemview: View, val cardListener1: CardListener): RecyclerView.ViewHolder(itemview){

        val binding = CardTodoBinding.bind(itemview)

        fun setData(data: Data){
            binding.todoTitleTV.text = data.Title

            if (data.Description.length <= 40) {
                binding.description.text = data.Description
            } else {
                binding.description.text = data.Description.substring(0, 40) + "    ..."
            }
            binding.dueDate.text = data.Due

            itemview.setOnClickListener{
                cardListener1.onCardClick(adapterPosition, data.ID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.card_todo, parent, false)
        return viewHolder(view, cardListener)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listNote[position])

    }

    override fun getItemCount(): Int {
        return listNote.size
    }
}