package dev.kchr.se.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.kchr.se.CardListener
import dev.kchr.se.R
import dev.kchr.se.databinding.CardTodoBinding
import dev.kchr.se.model.Data


class ToDoAdapter(private val listToDo: Array<Data>, val cardListener: CardListener) :
    RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {

    val initialToDoDataList = ArrayList<Data>().apply {
        listToDo?.let { addAll(it) }
    }

    class ViewHolder(val itemview: View, val cardListener1: CardListener) : RecyclerView.ViewHolder(itemview) {
        val binding = CardTodoBinding.bind(itemview)

        fun setData(data: Data) {
            binding.todoTitleTV.text = data.Title
            binding.todoDescriptionTV.text = data.Description
            binding.todoDueDateTV.text = data.DueDate

            itemview.setOnClickListener {
                cardListener1.onCardClick(adapterPosition, data.ID)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_todo, parent, false)

        return ViewHolder(view, cardListener)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.setData(listToDo[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return listToDo.size
    }

}


