package dev.kchr.se.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import dev.kchr.se.R

class ToDoAdapter: RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {
        return TODO("Provide the return value")
    }

    override fun onBindViewHolder(holder: ToDoAdapter.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
    inner  class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title: TextView
        var due: TextView
        var description: TextView

        init {
            title = itemView.findViewById(R.id.todoTitleTV)
            due = itemView.findViewById(R.id.dueDate)
            description = itemView.findViewById(R.id.description)
        }
    }
}

