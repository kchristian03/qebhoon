package dev.kchr.se.adapter

import android.provider.ContactsContract.Data
import dev.kchr.se.CardListener
import dev.kchr.se.R
import dev.kchr.se.databinding.CardTodoBinding
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.widget.Filter
import java.util.*
import kotlin.collections.ArrayList

class ToDoAdapter(val listNote: ArrayList<Data>, val cardListener: CardListener) :
    RecyclerView.Adapter<ToDoAdapter.viewHolder>() {

    val initialNotessDataList = ArrayList<Data>().apply {
        listNote?.let { addAll(it) }
    }

    class viewHolder (val itemview: View, val cardListener1: CardListener): RecyclerView.ViewHolder(itemview){

        val binding = CardTodoBinding.bind(itemview)

        fun setData(data: Data){
            binding.todoTitleTV.text = data.Title
//            data.Title.also { binding.noteJudulTV.text = it }
            if (data.Content.length <= 40) {
                binding.description.text = data.description
            } else {
                binding.description.text = data.Content.substring(0, 40) + "    ..."
            }

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

    fun getFilter(): Filter{
        return filter
    }

    private val filter = object: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = ArrayList<Data>()
            if (constraint == null || constraint.isEmpty()){
                initialNotessDataList.let { filteredList.addAll(it) }
//                filteredList.addAll(initialNotessDataList)
            } else {
                val query = constraint.toString().trim().lowercase(Locale.ROOT)
                initialNotessDataList.forEach {
                    if (it.Title.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(it)
                    }
                }
//                }
////                val filterPattern = constraint.toString().lowercase(Locale.ROOT).trim()
//                for (item in initialNotessDataList){
//                    if (item.Title.lowercase(Locale.ROOT).contains(filterPattern)){
//                        filteredList.add(item)
//                    }
//                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            listNote.clear()
            listNote.addAll(results?.values as ArrayList<Data>)
            notifyDataSetChanged()
        }



    }


}